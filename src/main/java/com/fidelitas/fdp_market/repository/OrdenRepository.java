package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Orden;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    public List<Orden> findByClienteId(Long clienteId);
}
