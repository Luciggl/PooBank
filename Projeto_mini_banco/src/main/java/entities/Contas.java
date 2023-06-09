package entities;

import java.io.Serializable;

public class Contas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Cliente cliente;
    private int numeroConta;
    private int numeroAgencia;
    private double saldo;


    public Contas(Cliente cliente, int numC, int numAg, double saldo) {
        this.cliente = cliente;
        this.numeroConta = numC;
        this.numeroAgencia = numAg;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "[------------------------------]\nNome: " + cliente.getNome() + "\nNumero da Conta: " + getNumeroConta() + "\nNumero da Agencia: " + getNumeroAgencia()
                + "\nCPF: " + cliente.getCpf() + "\nSaldo em conta: " + getSaldo() + "R$" + "\n[------------------------------]";
    }

    public String toStringNome() {
        return "Nome: " + cliente.getNome();
    }

    public String toStringCpf() {
        return "CPF: " + cliente.getCpf();
    }

    public String toStringNumC() {
        return "Numero da Conta: " + getNumeroConta();
    }

    public String toStringNumAg() {
        return "Numero da Agencia: " + getNumeroAgencia();
    }

    public String toStringSaldo() {
        return "Saldo: " + getSaldo() + "R$";
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Contas setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
        return this;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public Contas setSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public Contas setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
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


}
