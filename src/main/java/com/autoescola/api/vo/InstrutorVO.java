package com.autoescola.api.vo;

import com.autoescola.api.entity.Especialidade;

public class InstrutorVO {

    private Long id;
    private String nome;
    private String email;
    private String cnh;
    private Especialidade especialidade;

    // Constructors
    public InstrutorVO() {}

    public InstrutorVO(Long id, String nome, String email, String cnh, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnh = cnh;
        this.especialidade = especialidade;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}

