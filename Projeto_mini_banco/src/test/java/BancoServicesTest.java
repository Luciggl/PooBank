
import entities.Cliente;
import entities.ContaEspecial;
import entities.Contas;
import exception.ContaJaExisteException;
import exception.ContaNaoExisteException;
import exception.SaldoInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BancoServices;


import java.util.ArrayList;

class BancoServicesTest {
    private BancoServices bancoServices;
    private ArrayList<Contas> contas;

    @BeforeEach
    void setUp() {
        contas = new ArrayList<>();
        bancoServices = new BancoServices(contas);
    }

    @Test
    void abrirConta_deveAdicionarNovaContaNaLista() throws ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        // Act
        bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);

        // Assert
        Assertions.assertEquals(1, contas.size());
        Contas conta = contas.get(0);
        Assertions.assertEquals(cpf, conta.getCliente().getCpf());
        Assertions.assertEquals(numC, conta.getNumeroConta());
        Assertions.assertEquals(numAg, conta.getNumeroAgencia());
        Assertions.assertEquals(saldo, conta.getSaldo());
    }

    @Test
    void abrirConta_deveLancarContaJaExisteExceptionSeContaExistir() throws ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);

        // Act & Assert
        Assertions.assertThrows(ContaJaExisteException.class, () ->
                bancoServices.abrirConta(nome, cpf, numC, numAg, saldo));
    }

    @Test
    void removerConta_deveRemoverContaDaLista() throws ContaNaoExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        Contas conta = new Contas(new Cliente(nome, cpf), numC, numAg, saldo);
        contas.add(conta);

        // Act
        bancoServices.removerConta(numC, numAg);

        // Assert
        Assertions.assertEquals(0, contas.size());
        Assertions.assertFalse(contas.contains(conta));
    }

    @Test
    void removerConta_deveLancarContaNaoExisteExceptionSeContaNaoExistir() {
        // Arrange
        int numC = 123;
        int numAg = 456;

        // Act & Assert
        Assertions.assertThrows(ContaNaoExisteException.class, () ->
                bancoServices.removerConta(numC, numAg));
    }

    @Test
    void abrirContaEspecial_deveAdicionarNovaContaEspecialNaLista() throws ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;
        double credito = 1000.0;

        // Act
        bancoServices.abrirContaEspecial(nome, cpf, numC, numAg, saldo, credito);

        // Assert
        Assertions.assertEquals(1, contas.size());
        Contas conta = contas.get(0);
        Assertions.assertTrue(conta instanceof ContaEspecial);
        Assertions.assertEquals(nome, conta.getCliente().getNome());
        Assertions.assertEquals(cpf, conta.getCliente().getCpf());
        Assertions.assertEquals(numC, conta.getNumeroConta());
        Assertions.assertEquals(numAg, conta.getNumeroAgencia());
        Assertions.assertEquals(saldo, conta.getSaldo());
        Assertions.assertEquals(credito, ((ContaEspecial) conta).getCredito());
    }

    @Test
    void abrirContaEspecial_deveLancarContaJaExisteExceptionSeContaExistir() throws ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;
        double credito = 1000.0;

        bancoServices.abrirContaEspecial(nome, cpf, numC, numAg, saldo, credito);

        // Act & Assert
        Assertions.assertThrows(ContaJaExisteException.class, () ->
                bancoServices.abrirContaEspecial(nome, cpf, numC, numAg, saldo, credito));
    }

    @Test
    void pesquisarContasDoCliente_deveRetornarContaDoCliente() throws ContaNaoExisteException, ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);

        // Act
        Contas conta = bancoServices.pesquisarContasDoCliente(cpf);

        // Assert
        Assertions.assertNotNull(conta);
        Assertions.assertEquals(nome, conta.getCliente().getNome());
        Assertions.assertEquals(cpf, conta.getCliente().getCpf());
        Assertions.assertEquals(numC, conta.getNumeroConta());
        Assertions.assertEquals(numAg, conta.getNumeroAgencia());
        Assertions.assertEquals(saldo, conta.getSaldo());
    }

    @Test
    void pesquisarContasDoCliente_deveLancarContaNaoExisteExceptionSeContaNaoExistir() {
        // Arrange
        String cpf = "123456789";

        // Act & Assert
        Assertions.assertThrows(ContaNaoExisteException.class, () ->
                bancoServices.pesquisarContasDoCliente(cpf));
    }

    @Test
    void getContas_deveRetornarListaDeContas() {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        contas.add(new Contas(new Cliente(nome, cpf), numC, numAg, saldo));

        // Act
        ArrayList<Contas> result = bancoServices.getContas();

        // Assert
        Assertions.assertEquals(contas, result);
    }

    @Test
    void consultarSaldoDeConta_deveRetornarSaldoDaConta() throws ContaNaoExisteException, ContaJaExisteException {
        // Arrange
        String nome = "John Doe";
        String cpf = "123456789";
        int numC = 123;
        int numAg = 456;
        double saldo = 100.0;

        bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);

        // Act
        double result = bancoServices.consultarSaldoDeConta(numC, numAg);

        // Assert
        Assertions.assertEquals(saldo, result);
    }

    @Test
    void consultarSaldoDeConta_deveLancarContaNaoExisteExceptionSeContaNaoExistir() {
        // Arrange
        int numC = 123;
        int numAg = 456;

        // Act & Assert
        Assertions.assertThrows(ContaNaoExisteException.class, () ->
                bancoServices.consultarSaldoDeConta(numC, numAg));
    }

    @Test
    void transferirDeveTransferirValorDeContaOrigemParaContaDestino() throws ContaNaoExisteException, SaldoInvalidoException, ContaJaExisteException {
        // Arrange
        String nomeOrigem = "John Doe";
        String cpfOrigem = "123456789";
        int numCOrigem = 123;
        int numAgOrigem = 456;
        double saldoOrigem = 100.0;

        String nomeDestino = "Jane Smith";
        String cpfDestino = "987654321";
        int numCDestino = 456;
        int numAgDestino = 789;
        double saldoDestino = 50.0;

        bancoServices.abrirConta(nomeOrigem, cpfOrigem, numCOrigem, numAgOrigem, saldoOrigem);
        bancoServices.abrirConta(nomeDestino, cpfDestino, numCDestino, numAgDestino, saldoDestino);

        double valorTransferencia = 30.0;

        // Act
        bancoServices.transferir(numCOrigem, numAgOrigem, numCDestino, numAgDestino, valorTransferencia);

        // Assert
        Assertions.assertEquals(saldoOrigem - valorTransferencia, bancoServices.consultarSaldoDeConta(numCOrigem, numAgOrigem));
        Assertions.assertEquals(saldoDestino + valorTransferencia, bancoServices.consultarSaldoDeConta(numCDestino, numAgDestino));
    }

    @Test
    void transferirDeveLancarContaNaoExisteExceptionSeContaOrigemNaoExistir() {
        // Arrange
        int numCOrigem = 123;
        int numAgOrigem = 456;
        int numCDestino = 789;
        int numAgDestino = 987;
        double valorTransferencia = 50.0;

        // Act & Assert
        Assertions.assertThrows(ContaNaoExisteException.class, () ->
                bancoServices.transferir(numCOrigem, numAgOrigem, numCDestino, numAgDestino, valorTransferencia));
    }

    @Test
    void transferirDeveLancarContaNaoExisteExceptionSeContaDestinoNaoExistir() throws ContaJaExisteException {
        // Arrange
        String nomeOrigem = "John Doe";
        String cpfOrigem = "123456789";
        int numCOrigem = 123;
        int numAgOrigem = 456;
        double saldoOrigem = 100.0;

        int numCDestino = 789;
        int numAgDestino = 987;
        double valorTransferencia = 50.0;

        bancoServices.abrirConta(nomeOrigem, cpfOrigem, numCOrigem, numAgOrigem, saldoOrigem);

        // Act & Assert
        Assertions.assertThrows(ContaNaoExisteException.class, () ->
                bancoServices.transferir(numCOrigem, numAgOrigem, numCDestino, numAgDestino, valorTransferencia));
    }

    @Test
    void transferirDeveLancarSaldoInvalidoExceptionSeSaldoOrigemForMenorQueValorTransferencia() throws ContaNaoExisteException, ContaJaExisteException {
        // Arrange
        String nomeOrigem = "John Doe";
        String cpfOrigem = "123456789";
        int numCOrigem = 123;
        int numAgOrigem = 456;
        double saldoOrigem = 100.0;

        String nomeDestino = "Jane Smith";
        String cpfDestino = "987654321";
        int numCDestino = 456;
        int numAgDestino = 789;
        double saldoDestino = 50.0;

        bancoServices.abrirConta(nomeOrigem, cpfOrigem, numCOrigem, numAgOrigem, saldoOrigem);
        bancoServices.abrirConta(nomeDestino, cpfDestino, numCDestino, numAgDestino, saldoDestino);

        double valorTransferencia = 200.0;

        // Act & Assert
        Assertions.assertThrows(SaldoInvalidoException.class, () ->
                bancoServices.transferir(numCOrigem, numAgOrigem, numCDestino, numAgDestino, valorTransferencia));
    }
}