package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    private final CategoriaService categoriaService;

    public IndexController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    
    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "index";
    }
    
}
