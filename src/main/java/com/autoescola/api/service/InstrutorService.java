package com.autoescola.api.service;

import com.autoescola.api.dto.InstrutorDTO;
import com.autoescola.api.entity.Instrutor;
import com.autoescola.api.repository.InstrutorRepository;
import com.autoescola.api.vo.InstrutorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    public Instrutor cadastrarInstrutor(InstrutorDTO instrutorDTO) {
        // Validar se e-mail já existe
        if (instrutorRepository.existsByEmail(instrutorDTO.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        // Validar se CNH já existe
        if (instrutorRepository.existsByCnh(instrutorDTO.getCnh())) {
            throw new RuntimeException("CNH já cadastrada");
        }

        Instrutor instrutor = new Instrutor(
            instrutorDTO.getNome(),
            instrutorDTO.getEmail(),
            instrutorDTO.getTelefone(),
            instrutorDTO.getCnh(),
            instrutorDTO.getEspecialidade(),
            instrutorDTO.getEndereco()
        );

        return instrutorRepository.save(instrutor);
    }

    public Page<InstrutorVO> listarInstrutores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instrutor> instrutores = instrutorRepository.findByStatusTrueOrderByNomeAsc(pageable);
        
        return instrutores.map(instrutor -> new InstrutorVO(
            instrutor.getId(),
            instrutor.getNome(),
            instrutor.getEmail(),
            instrutor.getCnh(),
            instrutor.getEspecialidade()
        ));
    }

    public Instrutor atualizarInstrutor(Long id, InstrutorDTO instrutorDTO) {
        Optional<Instrutor> optionalInstrutor = instrutorRepository.findById(id);
        
        if (optionalInstrutor.isEmpty()) {
            throw new RuntimeException("Instrutor não encontrado");
        }

        Instrutor instrutor = optionalInstrutor.get();
        
        // Atualizar apenas campos permitidos
        instrutor.setNome(instrutorDTO.getNome());
        instrutor.setTelefone(instrutorDTO.getTelefone());
        instrutor.setEndereco(instrutorDTO.getEndereco());

        return instrutorRepository.save(instrutor);
    }

    public void excluirInstrutor(Long id) {
        Optional<Instrutor> optionalInstrutor = instrutorRepository.findById(id);
        
        if (optionalInstrutor.isEmpty()) {
            throw new RuntimeException("Instrutor não encontrado");
        }

        Instrutor instrutor = optionalInstrutor.get();
        instrutor.setStatus(false); // Exclusão lógica
        instrutorRepository.save(instrutor);
    }
}

