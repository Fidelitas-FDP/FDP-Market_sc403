package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.*;
import com.fidelitas.fdp_market.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;
    private final UsuarioRepository usuarioRepository;

    public OrdenService(OrdenRepository ordenRepository, EstadoOrdenRepository estadoOrdenRepository, UsuarioRepository usuarioRepository) {
        this.ordenRepository = ordenRepository;
        this.estadoOrdenRepository = estadoOrdenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // hu11 - crea orden, congela balance cliente para escrow
    @Transactional
    public Orden crearOrden(Long clienteId, Anuncio anuncio, int cantidad, String rsnComprador) {
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

        BigDecimal costoTotal = anuncio.getPrecioActual().multiply(BigDecimal.valueOf(cantidad));

        if (cliente.getSaldoBilletera().compareTo(costoTotal) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        // deduccion a cliente para flujo escrow
        cliente.setSaldoBilletera(cliente.getSaldoBilletera().subtract(costoTotal));
        usuarioRepository.save(cliente);

        EstadoOrden estado = estadoOrdenRepository.findByNombre("Retenida_Escrow")
                .orElseThrow(() -> new IllegalArgumentException("Estado no parametrizado"));

        // calculos para ledger de cobro (fee) - futuro se saca de valor atributo de tabla
        BigDecimal fee = costoTotal.multiply(new BigDecimal("0.02"));
        BigDecimal netoVendedor = costoTotal.subtract(fee);

        Orden orden = new Orden();
        orden.setCliente(cliente);
        orden.setAnuncio(anuncio);
        orden.setEstadoOrden(estado);
        orden.setCantidad(cantidad);
        orden.setPrecioUnitarioHistorico(anuncio.getPrecioActual());
        orden.setFeePlataforma(fee);
        orden.setNetoVendedorPadre(netoVendedor);
        orden.setComisionSubvendedor(BigDecimal.ZERO);
        orden.setRsnComprador(rsnComprador);
        orden.setIdChat(0L); // reemplazar cuando exista Chat

        return ordenRepository.save(orden);
    }

    // hu14 - marcar como entregado (Vendedor/sub)
    @Transactional
    public void marcarComoEntregado(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        EstadoOrden estado = estadoOrdenRepository.findByNombre("Entregada_Manual")
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

        orden.setEstadoOrden(estado);
        ordenRepository.save(orden);
    }

    // hu 15 - confirmar recibido y delegar liberacion de fondos (Cliente)
    @Transactional
    public void completarOrden(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        EstadoOrden estado = estadoOrdenRepository.findByNombre("Completada")
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

        orden.setEstadoOrden(estado);
        orden.setFechaLiberacion(LocalDateTime.now());

        // Vendedor recibe monto de escrow
        Usuario vendedor = orden.getAnuncio().getUsuario();
        vendedor.setSaldoBilletera(vendedor.getSaldoBilletera().add(orden.getNetoVendedorPadre()));

        usuarioRepository.save(vendedor);
        ordenRepository.save(orden);
    }

    @Transactional(readOnly = true)
    public List<Orden> listarOrdenesPorCliente(Long clienteId) {
        return ordenRepository.findByClienteId(clienteId);
    }

    @Transactional
    public void revertirAEscrow(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada"));

        EstadoOrden estado = estadoOrdenRepository.findByNombre("Retenida_Escrow")
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

        orden.setEstadoOrden(estado);
        ordenRepository.save(orden);
    }
}
