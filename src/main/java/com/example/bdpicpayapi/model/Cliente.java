package com.example.bdpicpayapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.context.properties.bind.Name;

@Entity
public class Cliente {

    @Id
    @CPF(message = "CPF inválido")
    @Schema(description = "ID único do produto", example = "1234")
    private String cpf;


    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 1, max = 255, message = "O campo nome deve ter entre 1 e 255 caracteres")
    @Schema(description = "nome do cliente", example = "John Doe")
    private String nome;

    @NotNull(message = "O campo email não pode ser nulo")
    @Email
    @Schema(description = "email do cliente", example = "johndoe@gmail.com")
    private String email;


    @NotNull(message = "O campo telefone não pode ser nulo")
    @Pattern(regexp = "[0-9]+", message = "O campo telefone deve conter apenas números")
    @Size(min = 11, max = 11, message = "O campo telefone deve ter 11 caracteres")
    @Schema(description = "telefone do cliente", example = "11956938057")
    private String telefone;

    public Cliente(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Cliente() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}


