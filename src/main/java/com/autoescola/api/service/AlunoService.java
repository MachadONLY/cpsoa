package com.autoescola.api.service;

import com.autoescola.api.dto.AlunoDTO;
import com.autoescola.api.entity.Aluno;
import com.autoescola.api.repository.AlunoRepository;
import com.autoescola.api.vo.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {
        // Validar se e-mail já existe
        if (alunoRepository.existsByEmail(alunoDTO.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        // Validar se CPF já existe
        if (alunoRepository.existsByCpf(alunoDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Aluno aluno = new Aluno(
            alunoDTO.getNome(),
            alunoDTO.getEmail(),
            alunoDTO.getTelefone(),
            alunoDTO.getCpf(),
            alunoDTO.getEndereco()
        );

        return alunoRepository.save(aluno);
    }

    public Page<AlunoVO> listarAlunos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Aluno> alunos = alunoRepository.findByStatusTrueOrderByNomeAsc(pageable);
        
        return alunos.map(aluno -> new AlunoVO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getEmail(),
            aluno.getCpf()
        ));
    }

    public Aluno atualizarAluno(Long id, AlunoDTO alunoDTO) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        
        if (optionalAluno.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        Aluno aluno = optionalAluno.get();
        
        // Atualizar apenas campos permitidos
        aluno.setNome(alunoDTO.getNome());
        aluno.setTelefone(alunoDTO.getTelefone());
        aluno.setEndereco(alunoDTO.getEndereco());

        return alunoRepository.save(aluno);
    }

    public void excluirAluno(Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        
        if (optionalAluno.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        Aluno aluno = optionalAluno.get();
        aluno.setStatus(false); // Exclusão lógica
        alunoRepository.save(aluno);
    }
}

