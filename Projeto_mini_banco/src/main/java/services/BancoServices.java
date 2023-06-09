    package services;

    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.ObjectOutputStream;
    import java.util.ArrayList;
    import java.util.Objects;

    import entities.Cliente;
    import entities.ContaEspecial;
    import entities.Contas;
    import exception.ContaJaExisteException;
    import exception.ContaNaoExisteException;
    import exception.SaldoInvalidoException;
    import repository.BancoRepository;

    public class BancoServices implements BancoRepository {
        private ArrayList<Contas> contas;


        public BancoServices(ArrayList<Contas> contas) {
            this.contas = contas;
        }

        @Override
        public void abrirConta(String nome, String cpf, int numC, int numAg, double saldo) throws ContaJaExisteException {
            if (existeConta(numC, numAg) || existeContaCpf(cpf)) {
                throw new ContaJaExisteException("Este contato já existe no sistema, tente novamente!");
            }
            Contas conta = new Contas(new Cliente(nome, cpf), numC, numAg, saldo);
            this.contas.add(conta);

        }

        public void removerConta(int numC, int numAg) throws ContaNaoExisteException {
            if (!existeConta(numC, numAg)) {
                throw new ContaNaoExisteException("Conta inválida! Tente novamente com uma conta válida!");
            } else {
                for (Contas c : this.contas) {
                    if (Objects.equals(c.getNumeroConta(), numC) && Objects.equals(c.getNumeroAgencia(), numAg)) {
                        contas.remove(c);
                        break;
                    }
                }
            }
        }

        @Override
        public void abrirContaEspecial(String nome, String cpf, int numC, int numAg, double saldo, double credito) throws ContaJaExisteException {
            if (existeConta(numC, numAg)) {
                throw new ContaJaExisteException("Está conta já foi cadastrada. Tente novamente com outra conta!");
            }
            ContaEspecial conta = new ContaEspecial(new Cliente(nome, cpf), numC, numAg, saldo, credito);
            this.contas.add(conta);
        }

        @Override
        public Contas pesquisarContasDoCliente(String cpf) throws ContaNaoExisteException {
            for (Contas c : this.contas) {
                if (c.cliente.getCpf().equals(cpf)) {
                    return c;
                }
            }
            throw new ContaNaoExisteException("Esta conta é inválida, tente novamente com uma conta válida!");
        }

        @Override
        public ArrayList<Contas> getContas() {
            return contas;
        }

        @Override
        public double consultarSaldoDeConta(int numConta, int numAgencia) throws ContaNaoExisteException {
            if (existeConta(numConta, numAgencia)) {
                double saldo = 0;
                for (Contas c : this.contas) {
                    if (Objects.equals(c.getNumeroConta(), numConta) && Objects.equals(c.getNumeroAgencia(), numAgencia)) {
                        saldo = c.getSaldo();
                    }
                }
                return saldo;
            }
            throw new ContaNaoExisteException("Não foi possível consultar o saldo de sua conta! Tente novamente com uma conta válida");
        }

        @Override
        public void transferir(int numContaO, int numAgO, int numContaD, int numAgenciaD, double valor) throws ContaNaoExisteException, SaldoInvalidoException {
            if (!existeConta(numContaO, numAgO) || !existeConta(numContaD, numAgenciaD)) {
                throw new ContaNaoExisteException("Uma das contas ou as duas são inválidas");
            } else {
                Contas contaO = null;
                Contas contaD = null;
                for (Contas c : this.contas) {
                    if (Objects.equals(c.getNumeroConta(), numContaO) && Objects.equals(c.getNumeroAgencia(), numAgO)) {
                        contaO = c;
                    }

                    if (Objects.equals(c.getNumeroConta(), numContaD) && Objects.equals(c.getNumeroAgencia(), numAgenciaD)) {
                        contaD = c;
                    }
                }
                if (contaO != null && contaD != null && contaO != contaD) {
                    if (contaO.getSaldo() <= valor) {
                        throw new SaldoInvalidoException("Não foi possível fazer a transferência, pois o saldo da conta Origem é menor que o valor a ser transferido!");
                    } else {
                        contaO.debitar(valor);
                        contaD.creditar(valor);
                    }
                } else {
                    throw new ContaNaoExisteException("As contas são iguais. Tente novamente alterando a conta Origem e/ou a conta Destino!");
                }
            }
        }

        @Override
        public void sacarDeConta(int numConta, int numAgencia, double valor) throws ContaNaoExisteException, SaldoInvalidoException {
            if (!existeConta(numConta, numAgencia)) {
                throw new ContaNaoExisteException("Essa conta é inválida, tente novamente com uma conta válida!");
            } else {
                for (Contas c : this.contas) {
                    if (Objects.equals(c.getNumeroConta(), numConta) && (Objects.equals(c.getNumeroAgencia(), numAgencia)) && c.getSaldo() >= 0) {
                        if (valor > c.getSaldo()) {
                            throw new SaldoInvalidoException("Não foi possível realizar o Saque! Seu saldo é Menor que o valor que deseja Sacar!");
                        } else {
                            c.debitar(valor);
                        }
                    }
                }
            }
        }

        @Override
        public void depositarEmConta(int numConta, int numAgencia, double valor) throws ContaNaoExisteException {
            if (!existeConta(numConta, numAgencia)) {
                throw new ContaNaoExisteException("Não foi possível depositar de sua conta! Tente novamente com uma conta válida!");
            } else {
                for (Contas c : this.contas) {
                    if (Objects.equals(c.getNumeroConta(), numConta) && (Objects.equals(c.getNumeroAgencia(), numAgencia))) {
                        c.creditar(valor);
                    }
                }
            }
        }

        @Override
        public boolean existeConta(int numConta, int numAgencia) {
            for (Contas c : this.contas) {
                if (Objects.equals(c.getNumeroConta(), numConta) && Objects.equals(c.getNumeroAgencia(), numAgencia)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean existeContaCpf(String cpf) {
            for (Contas c : this.contas) {
                if (c.cliente.getCpf().equals(cpf)) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean alterarNome(String cpf, String novoNome) throws ContaNaoExisteException {
            for (Contas c : this.contas) {
                if (c.cliente.getCpf().equals(cpf)) {
                    c.cliente.setNome(novoNome);
                    return true;
                }
            }
            throw new ContaNaoExisteException("Erro!");
        }
    }
