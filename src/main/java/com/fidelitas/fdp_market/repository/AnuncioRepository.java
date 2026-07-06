package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Anuncio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    
    // hu9 - filtra anuncios por categoria activa
    public List<Anuncio> findByActivoTrueAndCategoriaId(Long categoriaId);
    
    // rehabilitar anuncio
    public List<Anuncio> findByCategoriaId(Long categoriaId);
    
}
