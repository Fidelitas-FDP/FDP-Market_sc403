package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.InventarioSerial;
import com.fidelitas.fdp_market.repository.InventarioSerialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventarioSerialService {
    
    private final InventarioSerialRepository inventarioSerialRepository;

    public InventarioSerialService(InventarioSerialRepository inventarioSerialRepository) {
        this.inventarioSerialRepository = inventarioSerialRepository;
    }
    
    // hu13 - busca y asigna serial a orden
    @Transactional
    public InventarioSerial asignarCredencialAOrden(Long anuncioId, Long idOrden) {
        List<InventarioSerial> disponibles = inventarioSerialRepository.findByAnuncioIdAndIdOrdenAsignadaIsNull(anuncioId);
        
        if (disponibles.isEmpty()) {
            throw new IllegalStateException("No quedan seriales para este anuncio.");
        }
        
        // se busca serial libre
        InventarioSerial serial = disponibles.get(0);
        serial.setIdOrdenAsignada(idOrden);
        
        return inventarioSerialRepository.save(serial);
    }

    @Transactional(readOnly = true)
    public List<InventarioSerial> listarDisponiblesPorAnuncio(Long anuncioId) {
        return inventarioSerialRepository.findByAnuncioIdAndIdOrdenAsignadaIsNull(anuncioId);
    }
   
    
}
