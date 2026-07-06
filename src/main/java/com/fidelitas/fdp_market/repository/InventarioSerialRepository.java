package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.InventarioSerial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InventarioSerialRepository extends JpaRepository<InventarioSerial, Long> {

    // hu13 - popula seriales disponibles de anuncio
    public List<InventarioSerial> findByAnuncioIdAndIdOrdenAsignadaIsNull(Long anuncioId);
    
}
