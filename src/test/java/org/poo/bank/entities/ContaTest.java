package org.poo.bank.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.poo.bank.exception.ContaJaExisteException;
import org.poo.bank.exception.ContaNaoExisteException;
import org.poo.bank.exception.SaldoNaoDisponivelException;
import org.poo.bank.services.BancoServices;

import java.util.ArrayList;

class BancoServicesTest {

    private BancoServices bancoServices;
    private ArrayList<Conta> contas;

    @BeforeEach
    public void setup() {
        contas = new ArrayList<>();
        bancoServices = new BancoServices(contas);
    }

    @Test
    public void testTransferir() {
        // Criação de contas para teste
        Conta contaOrigem = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        Conta contaDestino = new Conta(new Cliente("Cliente B", "98765432100"), "002", "001", 500);

        contas.add(contaOrigem);
        contas.add(contaDestino);

        // Transferência válida
        Assertions.assertDoesNotThrow(() -> bancoServices.transferir("001", "001", "002", "001", 500));

        // Transferência inválida - conta origem inválida
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.transferir("003", "001", "002", "001", 500));

        // Transferência inválida - conta destino inválida
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.transferir("001", "001", "003", "001", 500));

        // Transferência inválida - conta origem e destino iguais
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.transferir("001", "001", "001", "001", 500));

        // Transferência inválida - saldo insuficiente na conta origem
        Assertions.assertThrows(SaldoNaoDisponivelException.class, () -> bancoServices.transferir("001", "001", "002", "001", 2000));
    }

    @Test
    public void testAbrirConta() {
        // Abertura de conta válida
        Assertions.assertDoesNotThrow(() -> bancoServices.abrirConta("Cliente A", "12345678900", "001", "001", 1000));

        // Abertura de conta inválida - conta já existe
        Assertions.assertThrows(ContaJaExisteException.class, () -> bancoServices.abrirConta("Cliente B", "98765432100", "001", "001", 500));
    }

    @Test
    public void testRemoverConta() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Remoção de conta válida
        Assertions.assertDoesNotThrow(() -> bancoServices.removerConta("001", "001"));

        // Remoção de conta inválida - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.removerConta("001", "001"));
    }

    @Test
    public void testConsultarSaldoDeConta() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Consulta de saldo de conta válida
        try {
            Assertions.assertEquals(1000, bancoServices.consultarSaldoDeConta("001", "001"));
        } catch (ContaNaoExisteException e) {
            throw new RuntimeException(e);
        }

        // Consulta de saldo de conta inválida - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.consultarSaldoDeConta("002", "001"));
    }

    @Test
    public void testSacarDeConta() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Saque válido
        Assertions.assertDoesNotThrow(() -> bancoServices.sacarDeConta("001", "001", 500));

        // Saque inválido - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.sacarDeConta("002", "001", 500));

        // Saque inválido - saldo insuficiente
        Assertions.assertThrows(SaldoNaoDisponivelException.class, () -> bancoServices.sacarDeConta("001", "001", 2000));
    }

    @Test
    public void testDepositarEmConta() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Depósito válido
        Assertions.assertDoesNotThrow(() -> bancoServices.depositarEmConta("001", "001", 500));

        // Depósito inválido - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.depositarEmConta("002", "001", 500));
    }

    @Test
    public void testPesquisarContasDoCliente() {
        // Criação de contas para teste
        Conta conta1 = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        Conta conta2 = new Conta(new Cliente("Cliente B", "98765432100"), "002", "001", 500);

        contas.add(conta1);
        contas.add(conta2);

        // Pesquisa de conta válida
        try {
            Assertions.assertEquals(conta1, bancoServices.pesquisarContasDoCliente("12345678900"));
        } catch (ContaNaoExisteException e) {
            throw new RuntimeException(e);
        }

        // Pesquisa de conta inválida - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.pesquisarContasDoCliente("11111111111"));
    }

    @Test
    public void testAbrirContaEspecial() {
        // Abertura de conta especial válida
        Assertions.assertDoesNotThrow(() -> bancoServices.abrirContaEspecial("Cliente A", "12345678900", "001", "001", 1000, 100));

        // Abertura de conta especial inválida - conta já existe
        Assertions.assertThrows(ContaJaExisteException.class, () -> bancoServices.abrirContaEspecial("Cliente B", "98765432100", "001", "001", 500, 100));
    }

    @Test
    public void testExisteConta() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Verificação de conta existente
        Assertions.assertTrue(bancoServices.existeConta("001", "001"));

        // Verificação de conta inexistente
        Assertions.assertFalse(bancoServices.existeConta("002", "001"));
    }

    @Test
    public void testExisteContaCpf() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Verificação de conta existente pelo CPF do cliente
        Assertions.assertTrue(bancoServices.existeContaCpf("12345678900"));

        // Verificação de conta inexistente pelo CPF do cliente
        Assertions.assertFalse(bancoServices.existeContaCpf("98765432100"));
    }

    @Test
    public void testPesquisaNumeroContaENumeroAgencia() {
        // Criação de contas para teste
        Conta conta1 = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        Conta conta2 = new Conta(new Cliente("Cliente B", "98765432100"), "002", "001", 500);

        contas.add(conta1);
        contas.add(conta2);

        // Pesquisa de conta válida
        Assertions.assertEquals(conta1, bancoServices.pesquisaNumeroContaENumeroAgencia("001", "001"));

        // Pesquisa de conta inválida - conta não existe
        Assertions.assertNull(bancoServices.pesquisaNumeroContaENumeroAgencia("003", "001"));
    }

    @Test
    public void testAlterarNome() {
        // Criação de conta para teste
        Conta conta = new Conta(new Cliente("Cliente A", "12345678900"), "001", "001", 1000);
        contas.add(conta);

        // Alteração de nome válida
        Assertions.assertDoesNotThrow(() -> bancoServices.alterarNome("12345678900", "Novo Nome"));

        // Alteração de nome inválida - conta não existe
        Assertions.assertThrows(ContaNaoExisteException.class, () -> bancoServices.alterarNome("98765432100", "Novo Nome"));
    }
}
