package org.poo.bank.entities;

import java.io.Serializable;

public class Conta implements Serializable {
    public Cliente cliente;
    private String numeroConta;
    private String numeroAgencia;
    private double saldo;

    public Conta() {
        this(new Cliente("", ""), "", "", 0);
    }

    public Conta(Cliente cliente, String numC, String numAg, double saldo) {
        this.cliente = cliente;
        this.numeroConta = numC;
        this.numeroAgencia = numAg;
        this.saldo = saldo;
    }



    @Override
    public String toString() {
        return "[------------------------------]\nNome: "+cliente.getNome()+"\nNúmero da Conta: " + getNumeroConta() + "\nNúmero da Agência: " + getNumeroAgencia()
                + "\nCPF: " + cliente.getCpfTitular() + "\nSaldo em conta: " + getSaldo() + "R$" + "\n[------------------------------]";
    }

    public String toStringNome() {
        return "Nome: "+cliente.getNome();
    }

    public String toStringCpf(){
        return "CPF: "+cliente.getCpfTitular();
    }
    public String toStringNumC(){
        return "Nº da Conta: "+ getNumeroConta();
    }

    public String toStringNumAg(){
        return "Nº da Agência: "+getNumeroAgencia();
    }

    public String toStringSaldo(){
        return "Saldo: "+getSaldo()+"R$";
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public String getNumeroAgencia() {
        return this.numeroAgencia;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setNumeroConta(String novoNumero) {
        this.numeroConta = novoNumero;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double creditar(double valor) {
        this.saldo += valor;
        return valor;

    }

    public double debitar(double valor) {
        this.saldo -= valor;
        return valor;
    }
}
