package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.ImagenAnuncio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenAnuncioRepository extends JpaRepository<ImagenAnuncio, Long> {
    
    public List<ImagenAnuncio> findByAnuncioId(Long anuncioId);    
}
