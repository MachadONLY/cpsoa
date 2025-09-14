package com.autoescola.api.vo;

import com.autoescola.api.entity.StatusAgendamento;
import java.time.LocalDateTime;

public class AgendamentoVO {

    private Long id;
    private String nomeAluno;
    private String nomeInstrutor;
    private LocalDateTime dataHoraInstrucao;
    private StatusAgendamento status;

    // Constructors
    public AgendamentoVO() {}

    public AgendamentoVO(Long id, String nomeAluno, String nomeInstrutor, LocalDateTime dataHoraInstrucao, StatusAgendamento status) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.nomeInstrutor = nomeInstrutor;
        this.dataHoraInstrucao = dataHoraInstrucao;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeInstrutor() {
        return nomeInstrutor;
    }

    public void setNomeInstrutor(String nomeInstrutor) {
        this.nomeInstrutor = nomeInstrutor;
    }

    public LocalDateTime getDataHoraInstrucao() {
        return dataHoraInstrucao;
    }

    public void setDataHoraInstrucao(LocalDateTime dataHoraInstrucao) {
        this.dataHoraInstrucao = dataHoraInstrucao;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
}

