package com.autoescola.api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AgendamentoDTO {

    @NotNull(message = "Aluno é obrigatório")
    private Long alunoId;

    private Long instrutorId; // Opcional

    @NotNull(message = "Data/Hora da instrução é obrigatória")
    private LocalDateTime dataHoraInstrucao;

    // Constructors
    public AgendamentoDTO() {}

    public AgendamentoDTO(Long alunoId, Long instrutorId, LocalDateTime dataHoraInstrucao) {
        this.alunoId = alunoId;
        this.instrutorId = instrutorId;
        this.dataHoraInstrucao = dataHoraInstrucao;
    }

    // Getters and Setters
    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Long instrutorId) {
        this.instrutorId = instrutorId;
    }

    public LocalDateTime getDataHoraInstrucao() {
        return dataHoraInstrucao;
    }

    public void setDataHoraInstrucao(LocalDateTime dataHoraInstrucao) {
        this.dataHoraInstrucao = dataHoraInstrucao;
    }
}

