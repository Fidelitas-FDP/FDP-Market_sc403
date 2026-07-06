package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.domain.Anuncio;
import com.fidelitas.fdp_market.service.AnuncioService;
import com.fidelitas.fdp_market.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/anuncio")
public class AnuncioController {

    private final AnuncioService anuncioService;
    private final CategoriaService categoriaService;

    public AnuncioController(AnuncioService anuncioService, CategoriaService categoriaService) {
        this.anuncioService = anuncioService;
        this.categoriaService = categoriaService;
    }

    // hu9 - catalogo filtrado por categoria
    @GetMapping("/catalogo/{categoriaId}")
    public String verCatalogo(@PathVariable Long categoriaId, Model model) {
        List<Anuncio> anuncios = anuncioService.listarPorCategoriaCompleta(categoriaId);
        model.addAttribute("anuncios", anuncios);
        return "anuncio/catalogo";
    }

    // hu8 - pausar visibilidad anuncio
    @PostMapping("/pausar")
    public String pausarAnuncio(@RequestParam Long idAnuncio, @RequestParam boolean activo, @RequestParam Long categoriaId) {
        anuncioService.cambiarEstadoVisibilidad(idAnuncio, activo);
        return "redirect:/anuncio/catalogo/" + categoriaId;
    }

    @PostMapping("/activar")
    public String activarAnuncio(@RequestParam Long idAnuncio, @RequestParam Long categoriaId) {
        anuncioService.cambiarEstadoVisibilidad(idAnuncio, true);
        return "redirect:/anuncio/catalogo/" + categoriaId;
    }

}
