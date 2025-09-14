package com.autoescola.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Aluno é obrigatório")
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @NotNull(message = "Data/Hora da instrução é obrigatória")
    @Column(nullable = false)
    private LocalDateTime dataHoraInstrucao;

    @Column(nullable = false)
    private Integer duracaoMinutos = 60;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status = StatusAgendamento.AGENDADO;

    // Constructors
    public Agendamento() {}

    public Agendamento(Aluno aluno, Instrutor instrutor, LocalDateTime dataHoraInstrucao) {
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.dataHoraInstrucao = dataHoraInstrucao;
        this.duracaoMinutos = 60;
        this.status = StatusAgendamento.AGENDADO;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public LocalDateTime getDataHoraInstrucao() {
        return dataHoraInstrucao;
    }

    public void setDataHoraInstrucao(LocalDateTime dataHoraInstrucao) {
        this.dataHoraInstrucao = dataHoraInstrucao;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public MotivoCancelamento getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
}

