package com.autoescola.api.repository;

import com.autoescola.api.entity.Agendamento;
import com.autoescola.api.entity.Aluno;
import com.autoescola.api.entity.Instrutor;
import com.autoescola.api.entity.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.aluno = :aluno AND DATE(a.dataHoraInstrucao) = :data AND a.status = :status")
    long countByAlunoAndDataAndStatus(@Param("aluno") Aluno aluno, @Param("data") LocalDate data, @Param("status") StatusAgendamento status);
    
    @Query("SELECT a FROM Agendamento a WHERE a.instrutor = :instrutor AND a.dataHoraInstrucao = :dataHora AND a.status = :status")
    List<Agendamento> findByInstrutorAndDataHoraInstrucaoAndStatus(@Param("instrutor") Instrutor instrutor, @Param("dataHora") LocalDateTime dataHora, @Param("status") StatusAgendamento status);
    
    @Query("SELECT i FROM Instrutor i WHERE i.status = true AND i.id NOT IN (SELECT a.instrutor.id FROM Agendamento a WHERE a.dataHoraInstrucao = :dataHora AND a.status = :status)")
    List<Instrutor> findInstrutoresDisponiveis(@Param("dataHora") LocalDateTime dataHora, @Param("status") StatusAgendamento status);
}

