package com.autoescola.api.controller;

import com.autoescola.api.dto.AgendamentoDTO;
import com.autoescola.api.dto.CancelamentoAgendamentoDTO;
import com.autoescola.api.entity.Agendamento;
import com.autoescola.api.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> agendarInstrucao(@Valid @RequestBody AgendamentoDTO agendamentoDTO) {
        try {
            Agendamento agendamento = agendamentoService.agendarInstrucao(agendamentoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/cancelar")
    public ResponseEntity<Agendamento> cancelarInstrucao(@Valid @RequestBody CancelamentoAgendamentoDTO cancelamentoDTO) {
        try {
            Agendamento agendamento = agendamentoService.cancelarInstrucao(cancelamentoDTO);
            return ResponseEntity.ok(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

