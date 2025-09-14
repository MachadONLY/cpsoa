package com.autoescola.api.controller;

import com.autoescola.api.dto.InstrutorDTO;
import com.autoescola.api.entity.Instrutor;
import com.autoescola.api.service.InstrutorService;
import com.autoescola.api.vo.InstrutorVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @PostMapping
    public ResponseEntity<Instrutor> cadastrarInstrutor(@Valid @RequestBody InstrutorDTO instrutorDTO) {
        try {
            Instrutor instrutor = instrutorService.cadastrarInstrutor(instrutorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(instrutor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<InstrutorVO>> listarInstrutores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<InstrutorVO> instrutores = instrutorService.listarInstrutores(page, size);
        return ResponseEntity.ok(instrutores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrutor> atualizarInstrutor(@PathVariable Long id, @Valid @RequestBody InstrutorDTO instrutorDTO) {
        try {
            Instrutor instrutor = instrutorService.atualizarInstrutor(id, instrutorDTO);
            return ResponseEntity.ok(instrutor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id) {
        try {
            instrutorService.excluirInstrutor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

