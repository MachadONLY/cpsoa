package com.autoescola.api.service;

import com.autoescola.api.dto.AgendamentoDTO;
import com.autoescola.api.dto.CancelamentoAgendamentoDTO;
import com.autoescola.api.entity.*;
import com.autoescola.api.repository.AgendamentoRepository;
import com.autoescola.api.repository.AlunoRepository;
import com.autoescola.api.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    public Agendamento agendarInstrucao(AgendamentoDTO agendamentoDTO) {
        // Validar se aluno existe e está ativo
        Optional<Aluno> optionalAluno = alunoRepository.findById(agendamentoDTO.getAlunoId());
        if (optionalAluno.isEmpty() || !optionalAluno.get().getStatus()) {
            throw new RuntimeException("Aluno não encontrado ou inativo");
        }
        Aluno aluno = optionalAluno.get();

        // Validar horário de funcionamento (segunda a sábado, 06:00 às 21:00)
        LocalDateTime dataHora = agendamentoDTO.getDataHoraInstrucao();
        if (dataHora.getDayOfWeek() == DayOfWeek.SUNDAY ||
            dataHora.getHour() < 6 || dataHora.getHour() >= 21) {
            throw new RuntimeException("Horário fora do funcionamento da auto-escola");
        }

        // Validar antecedência mínima de 30 minutos
        LocalDateTime agora = LocalDateTime.now();
        if (ChronoUnit.MINUTES.between(agora, dataHora) < 30) {
            throw new RuntimeException("Instrução deve ser agendada com antecedência mínima de 30 minutos");
        }

        // Validar máximo de 2 instruções por dia para o mesmo aluno
        long instrucoesNoDia = agendamentoRepository.countByAlunoAndDataAndStatus(
            aluno, dataHora.toLocalDate(), StatusAgendamento.AGENDADO);
        if (instrucoesNoDia >= 2) {
            throw new RuntimeException("Aluno já possui 2 instruções agendadas para este dia");
        }

        Instrutor instrutor = null;
        
        // Se instrutor foi especificado, validar se existe e está disponível
        if (agendamentoDTO.getInstrutorId() != null) {
            Optional<Instrutor> optionalInstrutor = instrutorRepository.findById(agendamentoDTO.getInstrutorId());
            if (optionalInstrutor.isEmpty() || !optionalInstrutor.get().getStatus()) {
                throw new RuntimeException("Instrutor não encontrado ou inativo");
            }
            instrutor = optionalInstrutor.get();
            
            // Verificar se instrutor já tem agendamento no mesmo horário
            List<Agendamento> agendamentosInstrutor = agendamentoRepository.findByInstrutorAndDataHoraInstrucaoAndStatus(
                instrutor, dataHora, StatusAgendamento.AGENDADO);
            if (!agendamentosInstrutor.isEmpty()) {
                throw new RuntimeException("Instrutor já possui agendamento neste horário");
            }
        } else {
            // Escolher instrutor aleatório disponível
            List<Instrutor> instrutoresDisponiveis = agendamentoRepository.findInstrutoresDisponiveis(
                dataHora, StatusAgendamento.AGENDADO);
            if (instrutoresDisponiveis.isEmpty()) {
                throw new RuntimeException("Nenhum instrutor disponível neste horário");
            }
            Random random = new Random();
            instrutor = instrutoresDisponiveis.get(random.nextInt(instrutoresDisponiveis.size()));
        }

        Agendamento agendamento = new Agendamento(aluno, instrutor, dataHora);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento cancelarInstrucao(CancelamentoAgendamentoDTO cancelamentoDTO) {
        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(cancelamentoDTO.getAgendamentoId());
        if (optionalAgendamento.isEmpty()) {
            throw new RuntimeException("Agendamento não encontrado");
        }

        Agendamento agendamento = optionalAgendamento.get();
        
        // Validar se agendamento não está já cancelado
        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new RuntimeException("Agendamento já está cancelado");
        }

        // Validar antecedência mínima de 24 horas
        LocalDateTime agora = LocalDateTime.now();
        if (ChronoUnit.HOURS.between(agora, agendamento.getDataHoraInstrucao()) < 24) {
            throw new RuntimeException("Cancelamento deve ser feito com antecedência mínima de 24 horas");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamento.setMotivoCancelamento(cancelamentoDTO.getMotivoCancelamento());
        
        return agendamentoRepository.save(agendamento);
    }
}

