package org.poo.bank.entities;

import java.io.Serializable;

public class ContaEspecial extends Conta implements Serializable {

    private double credito;

    public ContaEspecial(Cliente cliente,String numC, String numAg, double saldo, double credito){
        super(cliente,numC,numAg,saldo);
        this.credito = credito;
    }

    public ContaEspecial(){
        this(new Cliente("",""),"","",0,1000);
    }

    public String toString() {
        return "-------------------------------\n" +
                "Nome: "+cliente.getNome()+"\n" +
                "Número da Conta: " + getNumeroConta() + "\n" +
                "Número da Agência: " + getNumeroAgencia() + "\n" +
                "CPF: " + cliente.getCpfTitular() + "\n" +
                "Saldo em conta: " + getSaldo() + "R$" + "\n" +
                "Crédito disponível: "+this.credito+"R$\n" +
                "-------------------------------";
    }

    public String toStringCredito(){
        return "Credito: "+ getCredito();
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

}
