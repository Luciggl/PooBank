package org.poo.bank.services;

import org.poo.bank.entities.Cliente;
import org.poo.bank.entities.Conta;
import org.poo.bank.entities.ContaEspecial;
import org.poo.bank.exception.ContaNaoExisteException;
import org.poo.bank.exception.ContaJaExisteException;
import org.poo.bank.exception.SaldoNaoDisponivelException;
import org.poo.bank.repository.InterfaceBanco;

import java.util.ArrayList;

public class BancoServices implements InterfaceBanco {
    private ArrayList<Conta> contas;

    public BancoServices(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void transferir(String numContaO, String numAgO, String numContaD, String numAgD, double valor) throws ContaNaoExisteException, SaldoNaoDisponivelException {
        if(!existeConta(numContaO, numAgO) && !existeConta(numContaD, numAgD)){
            throw new ContaNaoExisteException("Uma das contas ou as duas são(é) inválida(s)");
        } else {
            Conta contaO  = null;
            Conta contaD = null;
            for(Conta c: this.contas){
                if(c.getNumeroConta().equals(numContaO) && c.getNumeroAgencia().equals(numAgO)){
                    contaO = c;
                }

                if(c.getNumeroConta().equals(numContaD) && c.getNumeroAgencia().equals(numAgD)){
                    contaD = c;
                }
            }
            if(contaO!=null && contaD!= null && contaO!=contaD){
                if(contaO.getSaldo()>=0 && valor > contaO.getSaldo()){
                    throw new SaldoNaoDisponivelException("Não foi possível fazer a trasnferência, pois o saldo da conta Origem é menor que o valor a ser transferido!");
                } else {
                    contaO.debitar(valor);
                    contaD.creditar(valor);
                }
            } else {
                throw new ContaNaoExisteException("As contas são iguais. Tente novamente alterando a conta Origem e(ou) a conta Destino!");
            }
        }

    }

    public void abrirConta(String nome, String cpf, String numC, String numAg, double saldo) throws ContaJaExisteException {
        if(existeConta(numC, numAg) || existeContaCpf(cpf)){
            throw new ContaJaExisteException("Este contato já existe no sistema, tente novamente!");
        }
        Conta conta = new Conta(new Cliente(nome,cpf), numC, numAg, 0);
        this.contas.add(conta);
    }

    public void removerConta(String numC, String numAg) throws ContaNaoExisteException {
        if(!existeConta(numC, numAg)){
            throw new ContaNaoExisteException("Conta inválida! Tente novamente com uma conta válida!");
        } else {
            for(Conta c :this.contas){
                if(c.getNumeroConta().equals(numC) && c.getNumeroAgencia().equals(numAg)){
                    contas.remove(c);
                    break;
                }
            }
        }
    }

    public double consultarSaldoDeConta(String numConta, String numAgencia) throws ContaNaoExisteException {
        if(existeConta(numConta, numAgencia)){
            double saldo = 0;
            for (Conta c : this.contas) {
                if (c.getNumeroConta().equals(numConta) && c.getNumeroAgencia().equals(numAgencia)) {
                    saldo = c.getSaldo();
                }
            }
            return saldo;
        }
        throw new ContaNaoExisteException("Não foi possível consultar o saldo de sua conta! Tente novamente com uma conta válida");
    }

    public void sacarDeConta(String numConta, String numAgencia, double valor) throws ContaNaoExisteException, SaldoNaoDisponivelException {
        if(!existeConta(numConta, numAgencia)){
            throw new ContaNaoExisteException("Essa conta é inválida, tente novamente com uma conta válida!");
        } else {
            for (Conta c: this.contas){
                if(c.getNumeroConta().equals(numConta) && (c.getNumeroAgencia().equals(numAgencia)) && c.getSaldo()>=0){
                    if(valor > c.getSaldo()){
                        throw new SaldoNaoDisponivelException("Não foi possível realizar o Saque! Seu saldo é Menor que o valor que deseja Sacar!");
                    } else {
                        c.debitar(valor);
                    }
                }
            }
        }
    }

    public void depositarEmConta( String numConta, String numAgencia, double valor) throws ContaNaoExisteException {
        if(!existeConta(numConta, numAgencia)){
            throw new ContaNaoExisteException("Não foi possível depositar de sua conta! Tente novamente com uma conta válida!");
        } else {
            for (Conta c: this.contas){
                if(c.getNumeroConta().equals(numConta) && (c.getNumeroAgencia().equals(numAgencia))){
                    c.creditar(valor);
                }
            }
        }
    }

    public Conta pesquisarContasDoCliente(String cpf) throws ContaNaoExisteException {
        for(Conta c: this.contas){
            if(c.cliente.getCpfTitular().equals(cpf)){
                return c;
            }
        }
        throw new ContaNaoExisteException("Esta conta é inválida, tente novamente com uma conta válida!");
    }

    public ArrayList<Conta> getContas(){
        return  this.contas;
    }

    public void abrirContaEspecial(String nome, String cpf, String numC, String numAg, double saldo, double credito) throws ContaJaExisteException{
        if(existeConta(numC, numAg)){
            throw new ContaJaExisteException("Está conta já foi cadastrada. Tente novamente com outra conta!");
        }
        ContaEspecial conta = new ContaEspecial(new Cliente(nome,cpf), numC, numAg, 0,1000);
        this.contas.add(conta);
    }
    public boolean existeConta(String numConta, String numAg){
        for(Conta c: this.contas){
            if(c.getNumeroConta().equals(numConta) && c.getNumeroAgencia().equals(numAg)){
                return true;
            }
        }
        return false;
    }

    public boolean existeContaCpf(String cpf){
        for(Conta c: this.contas){
            if(c.cliente.getCpfTitular().equals(cpf)){
                return true;
            }
        }
        return false;
    }

    public Conta pesquisaNumeroContaENumeroAgencia(String numC, String numAg){
        for(Conta c: this.contas){
            if(c.getNumeroConta().equals(numC) && c.getNumeroAgencia().equals(numAg)){
                return c;
            }
        }
       return null;
    }

    public boolean alterarNome(String cpf,String novoNome) throws ContaNaoExisteException {
        for(Conta c: this.contas) {
            if (c.cliente.getCpfTitular().equals(cpf)) {
                c.cliente.setNome(novoNome);
                return true;
            }
        }
        throw new ContaNaoExisteException("Erro!");
    }
}

