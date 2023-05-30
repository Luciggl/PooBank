package org.example.repository;

import org.example.entities.Conta;
import org.example.exception.ContaJaExisteException;
import org.example.exception.ContaListException;
import org.example.exception.ContaNaoExisteException;

import java.util.ArrayList;

public interface ContaRepository {
    Conta findByCpf(String cpf) throws ContaNaoExisteException;
    void create(String keyCpf, Conta EntityConta) throws ContaJaExisteException, ContaNaoExisteException;
    void delete(String keyCpf) throws ContaNaoExisteException, ContaJaExisteException;
    ArrayList<Conta> pesquisarConta(String cpf) throws ContaNaoExisteException;
    ArrayList<Conta> getContas();
    ArrayList<Conta> pesquisarContasComSaldoNegativo();
    double consultarSaldoDeConta(String cpf, String titular, int numConta ) throws ContaNaoExisteException;
    void transferir(String cpfO, int numContaO,String cpfD, int numContaD, double valor) throws ContaNaoExisteException;
    void sacarDeConta(String cpf, String titular, int numConta, double valor) throws ContaNaoExisteException;
    void depositarEmConta(String cpf, String titular, int numConta, double valor) throws ContaNaoExisteException;
    default void saveAllDB(String path) throws ContaListException {

    }
    default void recoverDB(String path) throws ContaListException{

    }

}
