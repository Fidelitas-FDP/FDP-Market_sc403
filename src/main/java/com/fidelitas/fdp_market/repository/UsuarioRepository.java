package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // genera query sql leyendo nombre de metodo
    Optional<Usuario> findByCorreo(String email);
}