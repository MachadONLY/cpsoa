package com.autoescola.api.repository;

import com.autoescola.api.entity.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    
    Page<Instrutor> findByStatusTrueOrderByNomeAsc(Pageable pageable);
    
    boolean existsByEmail(String email);
    
    boolean existsByCnh(String cnh);
}

