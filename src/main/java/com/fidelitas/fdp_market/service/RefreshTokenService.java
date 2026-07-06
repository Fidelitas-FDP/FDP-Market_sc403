package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.RefreshToken;
import com.fidelitas.fdp_market.repository.RefreshTokenRepository;
import com.fidelitas.fdp_market.repository.UsuarioRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UsuarioRepository usuarioRepository;

    public RefreshTokenService(RefreshTokenRepository repo, UsuarioRepository userRepo) {
        this.refreshTokenRepository = repo;
        this.usuarioRepository = userRepo;
    }

    public RefreshToken crearRefreshToken(Long usuarioId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsuario(usuarioRepository.findById(usuarioId).get());
        refreshToken.setToken(UUID.randomUUID().toString());
        // Expira en 7 días
        refreshToken.setFechaExpiracion(Instant.now().plusMillis(604800000L)); 
        
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verificarExpiracion(RefreshToken token) {
        if (token.getFechaExpiracion().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Token expirado, reautenticarse.");
        }
        return token;
    }
}