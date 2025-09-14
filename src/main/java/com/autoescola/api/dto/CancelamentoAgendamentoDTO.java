package com.autoescola.api.dto;

import com.autoescola.api.entity.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public class CancelamentoAgendamentoDTO {

    @NotNull(message = "Instrução é obrigatória")
    private Long agendamentoId;

    @NotNull(message = "Motivo do cancelamento é obrigatório")
    private MotivoCancelamento motivoCancelamento;

    // Constructors
    public CancelamentoAgendamentoDTO() {}

    public CancelamentoAgendamentoDTO(Long agendamentoId, MotivoCancelamento motivoCancelamento) {
        this.agendamentoId = agendamentoId;
        this.motivoCancelamento = motivoCancelamento;
    }

    // Getters and Setters
    public Long getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Long agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public MotivoCancelamento getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}

