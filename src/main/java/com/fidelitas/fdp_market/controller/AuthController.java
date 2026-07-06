package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.dto.LoginRequest;
import com.fidelitas.fdp_market.security.JwtUtils;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Esto le pide al manager que verifique correo y contraseña usando BCrypt
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        // 2. Establece la seguridad en el contexto actual
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Genera el token matemático usando la clase JwtUtils
        String jwt = jwtUtils.generarToken(authentication);

        // 4. Devuelve el token en un formato JSON limpio al cliente
        return ResponseEntity.ok(Map.of("accessToken", jwt));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        // logica para leer token y generar nuevo
        return ResponseEntity.ok(Map.of("accessToken", "nuevo-token-generado"));
    }

}
