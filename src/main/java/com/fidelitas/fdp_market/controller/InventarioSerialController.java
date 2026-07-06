package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.InventarioSerialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventario")
public class InventarioSerialController {
    
    private final InventarioSerialService inventarioSerialService;

    public InventarioSerialController(InventarioSerialService inventarioSerialService) {
        this.inventarioSerialService = inventarioSerialService;
    }
    
    // gestion de seriales disponibles (vendedor)
    @GetMapping("/anuncio/{anuncioId}")
    public String verDisponibles(@PathVariable Long anuncioId, Model model) {
        model.addAttribute("seriales", inventarioSerialService.listarDisponiblesPorAnuncio(anuncioId));
        return "inventario/seriales";
    }
    
}
