package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.CategoriaService;
import com.fidelitas.fdp_market.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistroController {

    private final UsuarioService usuarioService;
    private final CategoriaService categoriaService; // Variable agregada

    // Constructor modificado para inyectar el servicio de categorías
    public RegistroController(UsuarioService usuarioService, CategoriaService categoriaService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
    }

    // HU2: Endpoint para renderizar la interfaz visual de Login clásica sin Whitelabel
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        // Inyecta las categorías en el modelo para evitar fallos de renderizado en el menú superior
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "login";
    }

    @GetMapping("/usuario/registro")
    public String mostrarFormularioRegistro(Model model) {
        // LINEA AGREGADA: Mantiene vivas las categorías en la barra de navegación superior
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "usuario/registro";
    }

    @PostMapping("/usuario/registro")
    public String procesarRegistro(@RequestParam String correo,
            @RequestParam String password,
            @RequestParam String rol,
            Model model) {
        
        try {
            usuarioService.registrarUsuario(correo, password, rol);
            return "redirect:/login?registrado=true";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            // Inyecta las categorías en caso de volver por error para no romper la vista
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "usuario/registro";
        }
    }
}