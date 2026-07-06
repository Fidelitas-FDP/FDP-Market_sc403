package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.Rol;
import com.fidelitas.fdp_market.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {
    
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Rol> buscarPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Rol> buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
    
}
