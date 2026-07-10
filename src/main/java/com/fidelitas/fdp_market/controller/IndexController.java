package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.AnuncioService;
import com.fidelitas.fdp_market.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CategoriaService categoriaService;
    private final AnuncioService anuncioService;

    public IndexController(CategoriaService categoriaService, AnuncioService anuncioService) {
        this.categoriaService = categoriaService;
        this.anuncioService = anuncioService;
    }

    

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        // Inyecta por defecto los anuncios de la categoria 1 en el home base
        model.addAttribute("anuncios", anuncioService.listarPorCategoriaCompleta(1L));
        return "/index";
    }

}
