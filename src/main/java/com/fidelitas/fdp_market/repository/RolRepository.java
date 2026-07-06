package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    // hu1 - registro
    public Optional<Rol> findByNombre(String nombre);    
}
