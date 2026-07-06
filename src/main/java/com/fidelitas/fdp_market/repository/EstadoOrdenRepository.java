package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.EstadoOrden;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoOrdenRepository extends JpaRepository<EstadoOrden, Long> {
    
    public Optional<EstadoOrden> findByNombre(String nombre);
    
}
