package repository;

import java.io.IOException;
import java.util.ArrayList;

import entities.Contas;
import exception.ContaJaExisteException;
import exception.ContaNaoExisteException;
import exception.SaldoInvalidoException;

public interface BancoRepository{
    void abrirConta(String nome, String cpf, int numC, int numAg, double saldo) throws ContaJaExisteException, IOException;
    void abrirContaEspecial(String nome, String cpf, int numC, int numAg, double saldo, double credito) throws ContaJaExisteException;
    void removerConta(int numConta, int numAg) throws ContaNaoExisteException, IOException;
    Contas pesquisarContasDoCliente(String cpf) throws ContaNaoExisteException;
    ArrayList<Contas> getContas();
    double consultarSaldoDeConta(int numConta, int numAgencia) throws ContaNaoExisteException;
    void transferir(int numContaO, int numAgO,int numContaD, int numAgenciaD, double valor) throws ContaNaoExisteException, SaldoInvalidoException;
    void sacarDeConta(int numConta, int numAgencia, double valor) throws ContaNaoExisteException, SaldoInvalidoException;
    void depositarEmConta(int numConta, int numAgencia, double valor) throws ContaNaoExisteException;
    boolean existeConta(int numConta, int numAgencia);
    boolean existeContaCpf(String cpf);
    boolean alterarNome(String cpf,String novoNome) throws ContaNaoExisteException;
}
