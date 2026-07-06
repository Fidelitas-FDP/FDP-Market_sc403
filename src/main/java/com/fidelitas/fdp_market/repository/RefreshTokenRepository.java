package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    
    // cerrar sesion
    void deleteByUsuarioId(Long usuarioId);

    public void delete(RefreshToken token);
}