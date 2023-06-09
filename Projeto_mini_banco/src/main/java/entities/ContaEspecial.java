package entities;

import java.io.Serializable;

public class ContaEspecial extends Contas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double credito;

    public ContaEspecial(Cliente cliente, int numC, int numAg, double saldo, double credito) {
        super(cliente, numC, numAg, saldo);
        this.credito = credito;
    }


    public String toString() {
        return "------------------------------\nNome: " + cliente.getNome() + "\nNumero da Conta: " + getNumeroConta() + "\nNumero da Agencia: " + "\nCPF: " + getNumeroAgencia() + cliente.getCpf() + "\nSaldo em conta: " + getSaldo() + "R$" + "\nCredito disponivel: " + this.credito + "R$\n------------------------------";
    }

    public String toStringCredito() {
        return "Credito: " + getCredito();
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

}
