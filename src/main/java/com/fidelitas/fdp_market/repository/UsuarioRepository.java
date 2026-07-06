package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByCorreo(String correo);
    
    public boolean existsByCorreo(String correo);

}