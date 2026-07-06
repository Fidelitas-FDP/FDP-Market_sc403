package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.domain.Disputa;
import com.fidelitas.fdp_market.service.DisputaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disputa")
public class DisputaController {
    
    private final DisputaService disputaService;

    public DisputaController(DisputaService disputaService) {
        this.disputaService = disputaService;
    }
    
    
    // hu22 - panel para moderador
    @GetMapping("/panel")
    public String verPanel(Model model) {
        model.addAttribute("disputas", disputaService.listarDisputasActivas());
        return "disputa/panel_moderacion";
    }

    // hu23 - detalle de disputa
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Disputa disputa = disputaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Caso inexistente"));
        model.addAttribute("disputa", disputa);
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
