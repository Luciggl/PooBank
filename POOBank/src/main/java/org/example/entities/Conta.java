package org.example.entities;

import java.util.Objects;

public class Conta {
    private String titular;
    private String cpf;
    private int numCelular;
    private String email;
    private String senha;
    private int numConta;
    private double saldo;

    public Conta(String titular, String cpf, int numCelular, String email, String senha, int numConta, double saldo) {
        this.titular = titular;
        this.cpf = cpf;
        this.numCelular = numCelular;
        this.email = email;
        this.senha = senha;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public Conta setTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Conta setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public int getNumCelular() {
        return numCelular;
    }

    public Conta setNumCelular(int numCelular) {
        this.numCelular = numCelular;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Conta setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Conta setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public int getNumConta() {
        return numConta;
    }

    public Conta setNumConta(int numConta) {
        this.numConta = numConta;
        return this;
    }

    public double getSaldo() {
        return saldo;
    }

    public Conta setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public double creditar(double valor) {
        this.saldo += valor;
        return valor;
    }

    public double debitar(double valor) {
        this.saldo -= valor;
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta conta)) return false;
        return getNumConta() == conta.getNumConta() && getCpf().equals(conta.getCpf()) && getEmail().equals(conta.getEmail()) && getSenha().equals(conta.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf(), getEmail(), getSenha(), getNumConta());
    }

    @Override
    public String toString() {
        return "Conta{" +
                "titular='" + titular + '\'' +
                ", cpf='" + cpf + '\'' +
                ", numCelular=" + numCelular +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", numConta=" + numConta +
                ", saldo=" + saldo +
                '}';
    }
}
