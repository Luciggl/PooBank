package org.poo.bank.repository;

import org.poo.bank.exception.ContaJaExisteException;
import org.poo.bank.exception.ContaNaoExisteException;
import org.poo.bank.exception.SaldoNaoDisponivelException;
import org.poo.bank.entities.Conta;

import java.util.ArrayList;

    public interface InterfaceBanco {
        void abrirConta(String nome, String cpf, String numC, String numAg, double saldo) throws ContaJaExisteException;
        void abrirContaEspecial(String nome, String cpf, String numC, String numAg, double saldo, double credito) throws ContaJaExisteException;
        void removerConta(String numConta, String numAg) throws ContaNaoExisteException;
        Conta pesquisarContasDoCliente(String cpf) throws ContaNaoExisteException;
        ArrayList<Conta> getContas();
        double consultarSaldoDeConta(String numConta, String numAgencia) throws ContaNaoExisteException;
        void transferir(String numContaO, String numAgO,String numContaD, String numAgenciaD, double valor) throws ContaNaoExisteException, SaldoNaoDisponivelException;
        void sacarDeConta(String numConta, String numAgencia, double valor) throws ContaNaoExisteException, SaldoNaoDisponivelException;
        void depositarEmConta(String numConta, String numAgencia, double valor) throws ContaNaoExisteException;
        boolean existeConta(String numConta, String numAg);
        boolean existeContaCpf(String cpf);
        Conta pesquisaNumeroContaENumeroAgencia(String numC, String numAg);
        boolean alterarNome(String cpf,String novoNome) throws ContaNaoExisteException;
}

