package com.example.bdpicpayapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Conta {

    @Id
    @Size(min = 5, max = 5, message = "O campo número da conta deve ter 5 caracteres")
    private String numeroConta;

    @NotNull
    @DecimalMax(value = "99999.99")
    @DecimalMin(value = "0.00")
    private double saldo;

    @NotNull
    @DecimalMax(value = "99999.99")
    @DecimalMin(value = "0.00")
    private double limiteEspecial;


    @NotNull
    @CPF(message = "CPF inválido")
    private String clienteCPF;

    public Conta(){}

    public Conta(String numeroConta, double saldo, double limiteEspecial, String clienteCPF) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.limiteEspecial = limiteEspecial;
        this.clienteCPF = clienteCPF;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(double limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }

    public String getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }
}
