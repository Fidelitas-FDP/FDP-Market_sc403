package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.CategoriaService;
import com.fidelitas.fdp_market.service.OrdenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/orden")
public class OrdenController {

    private final OrdenService ordenService;
    private final CategoriaService categoriaService; // Variable agregada

    // Constructor modificado para inyectar el servicio de categorías
    public OrdenController(OrdenService ordenService, CategoriaService categoriaService) {
        this.ordenService = ordenService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/mis-compras/{clienteId}")
    public String listarCompras(@PathVariable Long clienteId, Model model) {
        model.addAttribute("ordenes", ordenService.listarOrdenesPorCliente(clienteId));
        
        model.addAttribute("categorias", categoriaService.listarTodas());
        
        return "orden/mis_compras";
    }

    // hu14 - endpoint para confirmar entrega por Vendedor/sub
    @PostMapping("/entregar")
    public String entegarManual(@RequestParam Long idOrden, @RequestParam Long clienteId) {
        ordenService.marcarComoEntregado(idOrden);
        return "redirect:/orden/mis-compras/" + clienteId;
    }

    // hu15 - endpoint para confirmar recepcion desde Cliente
    @PostMapping("/confirmar")
    public String confirmarRecepcion(@RequestParam Long idOrden, @RequestParam Long clienteId) {
        ordenService.completarOrden(idOrden);
        return "redirect:/orden/mis-compras/" + clienteId;
    }

    @PostMapping("/revertir")
    public String revertirOrden(@RequestParam Long idOrden, @RequestParam Long clienteId) {
        ordenService.revertirAEscrow(idOrden);
        return "redirect:/orden/mis-compras/" + clienteId;
    }
}