package com.fidelitas.fdp_market.repository;

import com.fidelitas.fdp_market.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
}
