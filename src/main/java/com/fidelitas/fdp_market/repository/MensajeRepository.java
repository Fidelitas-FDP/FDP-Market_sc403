package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    
    // hu23 - carga/popula conversacion con mensajes
    public List<Mensaje> findByChatIdOrderByFechaAsc(Long chatId);
    
}
