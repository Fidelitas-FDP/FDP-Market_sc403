package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "usuario/registro";
    }
    

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String correo,
            @RequestParam String password,
            @RequestParam String rol,
            Model model) {
        
        try {
            // Cliente / Vendedor
            usuarioService.registrarUsuario(correo, password, rol);
            return "redirect:/login?registrado=true";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "usuario/registro";
        }
    }

}
