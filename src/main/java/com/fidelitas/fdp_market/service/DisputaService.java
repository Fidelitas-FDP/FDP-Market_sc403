package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.*;
import com.fidelitas.fdp_market.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class DisputaService {
    
    private final DisputaRepository disputaRepository;
    private final OrdenRepository ordenRepository;
    private final EstadoOrdenRepository estadoOrdenRepository;
    private final UsuarioRepository usuarioRepository;

    public DisputaService(DisputaRepository disputaRepository, OrdenRepository ordenRepository, EstadoOrdenRepository estadoOrdenRepository, UsuarioRepository usuarioRepository) {
        this.disputaRepository = disputaRepository;
        this.ordenRepository = ordenRepository;
        this.estadoOrdenRepository = estadoOrdenRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    // hu 21
    @Transactional
    public Disputa abrirDisputa(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden no valida"));

        EstadoOrden estadoDisputa = estadoOrdenRepository.findByNombre("Disputada")
                .orElseThrow(() -> new IllegalArgumentException("Estado Disputada no parametrizado"));
        
        orden.setEstadoOrden(estadoDisputa);
        ordenRepository.save(orden);

        Disputa disputa = new Disputa();
        disputa.setOrden(orden);
        
        return disputaRepository.save(disputa);
    }

    // hu 24
    @Transactional
    public void resolverDisputa(Long disputaId, Long moderadorId, String tipo, String justificacion) {
        Disputa disputa = disputaRepository.findById(disputaId)
                .orElseThrow(() -> new IllegalArgumentException("Disputa invalida"));
        Usuario mod = usuarioRepository.findById(moderadorId)
                .orElseThrow(() -> new IllegalArgumentException("Moderador inexistente"));
        
        Orden orden = disputa.getOrden();
        String estadoFinalNombre = tipo.equals("Liberacion") ? "Completada" : "Reembolsada";
        
        EstadoOrden estadoFinal = estadoOrdenRepository.findByNombre(estadoFinalNombre)
                .orElseThrow(() -> new IllegalArgumentException("Estado no existe"));

        orden.setEstadoOrden(estadoFinal);
        orden.setFechaLiberacion(LocalDateTime.now());
        ordenRepository.save(orden);

        // no se reembolsa
        if (tipo.equals("Liberacion")) {
            Usuario vendedor = orden.getAnuncio().getUsuario();
            vendedor.setSaldoBilletera(vendedor.getSaldoBilletera().add(orden.getNetoVendedorPadre()));
            usuarioRepository.save(vendedor);
            
        } else { // reembolso total
            Usuario cliente = orden.getCliente();
            BigDecimal costoTotal = orden.getPrecioUnitarioHistorico().multiply(BigDecimal.valueOf(orden.getCantidad()));
            cliente.setSaldoBilletera(cliente.getSaldoBilletera().add(costoTotal));
            usuarioRepository.save(cliente);
            disputa.setMontoReembolsado(costoTotal);
        }

        disputa.setModerador(mod);
        disputa.setResolucionTipo(tipo);
        disputa.setJustificacionTexto(justificacion);
        disputa.setFechaCierre(LocalDateTime.now());
        
        disputaRepository.save(disputa);
    }

    @Transactional(readOnly = true)
    public List<Disputa> listarDisputasActivas() {
        return disputaRepository.findByFechaCierreIsNull();
    }

    @Transactional(readOnly = true)
    public Optional<Disputa> buscarPorId(Long id) {
        return disputaRepository.findById(id);
    }
    
}
