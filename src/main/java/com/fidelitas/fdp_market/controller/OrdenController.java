package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.domain.Orden;
import com.fidelitas.fdp_market.service.OrdenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orden")
public class OrdenController {
    
    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }
    
    
    @GetMapping("/mis-compras/{clienteId}")
    public String listarCompras(@PathVariable Long clienteId, Model model) {
        model.addAttribute("ordenes", ordenService.listarOrdenesPorCliente(clienteId));
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
    
}
