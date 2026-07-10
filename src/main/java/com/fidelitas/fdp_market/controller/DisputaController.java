package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.domain.Disputa;
import com.fidelitas.fdp_market.service.CategoriaService;
import com.fidelitas.fdp_market.service.DisputaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disputa")
public class DisputaController {
    
    private final DisputaService disputaService;
    private final CategoriaService categoriaService; // Variable agregada

    // Constructor modificado para inyectar el servicio de categorías
    public DisputaController(DisputaService disputaService, CategoriaService categoriaService) {
        this.disputaService = disputaService;
        this.categoriaService = categoriaService;
    }
    
    // hu22 - panel para moderador (Modificado para mantener persistencia del menú)
    @GetMapping("/panel")
    public String verPanel(Model model) {
        model.addAttribute("disputas", disputaService.listarDisputasActivas());
        
        // LINEA AGREGADA: Inyecta las categorías en el modelo del panel de soporte
        model.addAttribute("categorias", categoriaService.listarTodas());
        
        return "disputa/panel_moderacion";
    }

    // hu23 - detalle de disputa (Modificado para mantener persistencia del menú)
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Disputa disputa = disputaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Caso inexistente"));
        model.addAttribute("disputa", disputa);
        
        model.addAttribute("categorias", categoriaService.listarTodas());
        
        return "disputa/detalle_caso";
    }

    // hu24
    @PostMapping("/resolver")
    public String resolver(@RequestParam Long idDisputa, @RequestParam Long idModerador,
                           @RequestParam String tipo, @RequestParam String justificacion) {
        disputaService.resolverDisputa(idDisputa, idModerador, tipo, justificacion);
        return "redirect:/disputa/panel";
    }
}
