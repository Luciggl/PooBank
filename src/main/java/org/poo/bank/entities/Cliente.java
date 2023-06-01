package org.poo.bank.entities;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String cpfTitular;

    public Cliente(String nome, String cpfTitular) {
        this.nome = nome;
        this.cpfTitular = cpfTitular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

}
