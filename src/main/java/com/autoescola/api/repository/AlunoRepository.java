package com.autoescola.api.repository;

import com.autoescola.api.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    Page<Aluno> findByStatusTrueOrderByNomeAsc(Pageable pageable);
    
    boolean existsByEmail(String email);
    
    boolean existsByCpf(String cpf);
}

