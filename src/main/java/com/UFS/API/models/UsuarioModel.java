package com.UFS.API.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Usuarios")
public class UsuarioModel extends RepresentationModel<UsuarioModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private long cpf;
    private String nome;
    private LocalDate data_nascimento;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
