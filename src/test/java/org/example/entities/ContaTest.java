package org.example.entities;
import org.example.entities.Conta;
import org.example.exception.ContaJaExisteException;
import org.example.exception.ContaNaoExisteException;
import org.example.repository.ContaRepository;
import org.example.services.ContaServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContaTest {

    private ContaRepository contaRepository;

    @Before
    public void setup() {
        contaRepository = new ContaServices();
    }

    @Test
    public void testCreate() {
        try {
            Conta conta1 = new Conta("Titular 1", "11111111111", 123456789, "titular1@example.com", "senha123", 1, 100.0);
            contaRepository.create(conta1.getCpf(), conta1);
            Conta contaEncontrada = contaRepository.findByCpf("11111111111");
            assertEquals(conta1, contaEncontrada);
            contaRepository.delete("11111111111");
        } catch (ContaJaExisteException | ContaNaoExisteException e) {
            Assert.fail("Erro ao criar conta: " + e.getMessage());
        }
    }

    @Test
    public void testFindByCpf() {
        try {
            Conta conta1 = new Conta("Titular 1", "11111111111", 123456789, "titular1@example.com", "senha123", 1, 100.0);
            contaRepository.create(conta1.getCpf(), conta1);
            Conta contaEncontrada = contaRepository.findByCpf("11111111111");
            assertEquals(conta1, contaEncontrada);
            contaRepository.delete("11111111111");
        } catch (ContaJaExisteException | ContaNaoExisteException e) {
            Assert.fail("Erro ao pesquisar conta por CPF: " + e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            Conta conta1 = new Conta("Titular 1", "11111111111", 123456789, "titular1@example.com", "senha123", 1, 100.0);
            contaRepository.create(conta1.getCpf(), conta1);
            contaRepository.delete("11111111111");
            List<Conta> contasEncontradas = contaRepository.pesquisarConta("11111111111");
            assertEquals(0, contasEncontradas.size());
        } catch (ContaJaExisteException | ContaNaoExisteException e) {
            Assert.fail("Erro ao excluir conta: " + e.getMessage());
        }
    }

    @Test
    public void testPesquisarConta() {
        try {
            Conta conta1 = new Conta("Titular 1", "11111111111", 123456789, "titular1@example.com", "senha123", 1, 100.0);
            Conta conta2 = new Conta("Titular 1", "22222222222", 123456789, "titular1@example.com", "senha123", 2, 200.0);
            contaRepository.create(conta1.getCpf(), conta1);
            contaRepository.create(conta2.getCpf(), conta2);

            ArrayList<Conta> contasEncontradas = contaRepository.pesquisarConta("11111111111");
            assertEquals(1, contasEncontradas.size());
            assertEquals(conta1, contasEncontradas.get(0));

            contasEncontradas = contaRepository.pesquisarConta("22222222222");
            assertEquals(1, contasEncontradas.size());
            assertEquals(conta2, contasEncontradas.get(0));

            contaRepository.delete("11111111111");
            contaRepository.delete("22222222222");
        } catch (ContaJaExisteException | ContaNaoExisteException e) {
            Assert.fail("Erro ao pesquisar conta: " + e.getMessage());
        }
    }

    @Test
    public void testCreditar() {
        Conta conta = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        double valorCreditado = conta.creditar(50.0);
        assertEquals(150.0, conta.getSaldo(), 0.0);
        assertEquals(50.0, valorCreditado, 0.0);
    }

    @Test
    public void testDebitar() {
        Conta conta = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        double valorDebitado = conta.debitar(50.0);
        assertEquals(50.0, conta.getSaldo(), 0.0);
        assertEquals(50.0, valorDebitado, 0.0);
    }

    @Test
    public void testEquals() {
        Conta conta1 = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        Conta conta2 = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        Conta conta3 = new Conta("OutroTitular", "987654321", 987654321, "outroemail@example.com",
                "outrasenha", 5678, 200.0);

        assertTrue(conta1.equals(conta2));
        assertFalse(conta1.equals(conta3));
    }

    @Test
    public void testHashCode() {
        Conta conta1 = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        Conta conta2 = new Conta("Titular", "123456789", 123456789, "email@example.com",
                "senha", 1234, 100.0);

        Conta conta3 = new Conta("OutroTitular", "987654321", 987654321, "outroemail@example.com",
                "outrasenha", 5678, 200.0);

        assertEquals(conta1.hashCode(), conta2.hashCode());
        assertNotEquals(conta1.hashCode(), conta3.hashCode());
    }
}
