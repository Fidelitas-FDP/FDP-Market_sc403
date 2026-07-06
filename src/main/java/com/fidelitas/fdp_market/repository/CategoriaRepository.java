package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // categorias sin padre (principales)
    public List<Categoria> findByPadreIsNull();    
}
