package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.Categoria;
import com.fidelitas.fdp_market.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Categoria> listarCategoriasPrincipales() {
        return categoriaRepository.findByPadreIsNull();
    }

    @Transactional(readOnly = true)
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
}
