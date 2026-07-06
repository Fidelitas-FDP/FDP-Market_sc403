package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Disputa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputaRepository extends JpaRepository<Disputa, Long> {
    
    // hu22 - filtrar disputas activas
    public List<Disputa> findByFechaCierreIsNull();
    
}
