package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    private final CategoriaService CategoriaService;

    public CategoriaController(CategoriaService CategoriaService) {
        this.CategoriaService = CategoriaService;
    }
    
    @GetMapping("/listado")
    public String listar(Model model) {
        model.addAttribute("categorias", CategoriaService.listarCategoriasPrincipales());
        return "categoria/listado";
    }    
    
}
