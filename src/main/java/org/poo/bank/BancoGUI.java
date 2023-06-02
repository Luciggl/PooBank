package org.poo.bank;

import org.poo.bank.entities.Conta;
import org.poo.bank.exception.ContaJaExisteException;
import org.poo.bank.exception.ContaNaoExisteException;
import org.poo.bank.exception.SaldoNaoDisponivelException;
import org.poo.bank.services.BancoServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BancoGUI extends JFrame {
    private BancoServices bancoServices;
    private JTextField numContaOField;
    private JTextField numAgOField;
    private JTextField numContaDField;
    private JTextField numAgDField;
    private JTextField valorField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField numCField;
    private JTextField numAgField;
    private JTextField saldoField;
    private JTextField numCRemoverField;
    private JTextField numAgRemoverField;
    private JTextField numCConsultaField;
    private JTextField numAgConsultaField;
    private JTextField numCSaqueField;
    private JTextField numAgSaqueField;
    private JTextField valorSaqueField;
    private JTextField numCDepositoField;
    private JTextField numAgDepositoField;
    private JTextField valorDepositoField;
    private JTextField cpfPesquisaField;
    private JTextField novoNomeField;

    public BancoGUI(BancoServices bancoServices) {
        this.bancoServices = bancoServices;
        initialize();
    }

    private void initialize() {
        setTitle("Banco GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        JPanel transferPanel = createTransferPanel();
        JPanel abrirContaPanel = createAbrirContaPanel();
        JPanel removerContaPanel = createRemoverContaPanel();
        JPanel consultarSaldoPanel = createConsultarSaldoPanel();
        JPanel sacarPanel = createSacarPanel();
        JPanel depositarPanel = createDepositarPanel();
        JPanel pesquisarContaPanel = createPesquisarContaPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Transferir", transferPanel);
        tabbedPane.addTab("Abrir Conta", abrirContaPanel);
        tabbedPane.addTab("Remover Conta", removerContaPanel);
        tabbedPane.addTab("Consultar Saldo", consultarSaldoPanel);
        tabbedPane.addTab("Sacar", sacarPanel);
        tabbedPane.addTab("Depositar", depositarPanel);
        tabbedPane.addTab("Pesquisar Conta", pesquisarContaPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createTransferPanel() {
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new GridLayout(6, 2));

        JLabel numContaOLabel = new JLabel("Número da Conta Origem:");
        numContaOField = new JTextField();
        JLabel numAgOLabel = new JLabel("Número da Agência Origem:");
        numAgOField = new JTextField();
        JLabel numContaDLabel = new JLabel("Número da Conta Destino:");
        numContaDField = new JTextField();
        JLabel numAgDLabel = new JLabel("Número da Agência Destino:");
        numAgDField = new JTextField();
        JLabel valorLabel = new JLabel("Valor:");
        valorField = new JTextField();

        JButton transferButton = new JButton("Transferir");
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numContaO = numContaOField.getText();
                String numAgO = numAgOField.getText();
                String numContaD = numContaDField.getText();
                String numAgD = numAgDField.getText();
                double valor = Double.parseDouble(valorField.getText());

                try {
                    bancoServices.transferir(numContaO, numAgO, numContaD, numAgD, valor);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Transferência realizada com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Uma das contas ou as duas são inválidas");
                } catch (SaldoNaoDisponivelException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Não foi possível fazer a transferência, pois o saldo da conta Origem é menor que o valor a ser transferido");
                }
            }
        });

        transferPanel.add(numContaOLabel);
        transferPanel.add(numContaOField);
        transferPanel.add(numAgOLabel);
        transferPanel.add(numAgOField);
        transferPanel.add(numContaDLabel);
        transferPanel.add(numContaDField);
        transferPanel.add(numAgDLabel);
        transferPanel.add(numAgDField);
        transferPanel.add(valorLabel);
        transferPanel.add(valorField);
        transferPanel.add(new JLabel());
        transferPanel.add(transferButton);

        return transferPanel;
    }

    private JPanel createAbrirContaPanel() {
        JPanel abrirContaPanel = new JPanel();
        abrirContaPanel.setLayout(new GridLayout(6, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();
        JLabel numCLabel = new JLabel("Número da Conta:");
        numCField = new JTextField();
        JLabel numAgLabel = new JLabel("Número da Agência:");
        numAgField = new JTextField();
        JLabel saldoLabel = new JLabel("Saldo Inicial:");
        saldoField = new JTextField();

        JButton abrirContaButton = new JButton("Abrir Conta");
        abrirContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String numC = numCField.getText();
                String numAg = numAgField.getText();
                double saldo = Double.parseDouble(saldoField.getText());

                try {
                    bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Conta aberta com sucesso!");
                } catch (ContaJaExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Esta conta já existe no sistema, tente novamente!");
                }
            }
        });

        abrirContaPanel.add(nomeLabel);
        abrirContaPanel.add(nomeField);
        abrirContaPanel.add(cpfLabel);
        abrirContaPanel.add(cpfField);
        abrirContaPanel.add(numCLabel);
        abrirContaPanel.add(numCField);
        abrirContaPanel.add(numAgLabel);
        abrirContaPanel.add(numAgField);
        abrirContaPanel.add(saldoLabel);
        abrirContaPanel.add(saldoField);
        abrirContaPanel.add(new JLabel());
        abrirContaPanel.add(abrirContaButton);

        return abrirContaPanel;
    }

    private JPanel createRemoverContaPanel() {
        JPanel removerContaPanel = new JPanel();
        removerContaPanel.setLayout(new GridLayout(3, 2));

        JLabel numCRemoverLabel = new JLabel("Número da Conta:");
        numCRemoverField = new JTextField();
        JLabel numAgRemoverLabel = new JLabel("Número da Agência:");
        numAgRemoverField = new JTextField();

        JButton removerContaButton = new JButton("Remover Conta");
        removerContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numC = numCRemoverField.getText();
                String numAg = numAgRemoverField.getText();

                try {
                    bancoServices.removerConta(numC, numAg);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Conta removida com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Conta inválida! Tente novamente com uma conta válida!");
                }
            }
        });

        removerContaPanel.add(numCRemoverLabel);
        removerContaPanel.add(numCRemoverField);
        removerContaPanel.add(numAgRemoverLabel);
        removerContaPanel.add(numAgRemoverField);
        removerContaPanel.add(new JLabel());
        removerContaPanel.add(removerContaButton);

        return removerContaPanel;
    }

    private JPanel createConsultarSaldoPanel() {
        JPanel consultarSaldoPanel = new JPanel();
        consultarSaldoPanel.setLayout(new GridLayout(3, 2));

        JLabel numCConsultaLabel = new JLabel("Número da Conta:");
        numCConsultaField = new JTextField();
        JLabel numAgConsultaLabel = new JLabel("Número da Agência:");
        numAgConsultaField = new JTextField();

        JButton consultarSaldoButton = new JButton("Consultar Saldo");
        consultarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numC = numCConsultaField.getText();
                String numAg = numAgConsultaField.getText();

                try {
                    double saldo = bancoServices.consultarSaldoDeConta(numC, numAg);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Saldo da conta: " + saldo);
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Não foi possível consultar o saldo da conta! Tente novamente com uma conta válida");
                }
            }
        });

        consultarSaldoPanel.add(numCConsultaLabel);
        consultarSaldoPanel.add(numCConsultaField);
        consultarSaldoPanel.add(numAgConsultaLabel);
        consultarSaldoPanel.add(numAgConsultaField);
        consultarSaldoPanel.add(new JLabel());
        consultarSaldoPanel.add(consultarSaldoButton);

        return consultarSaldoPanel;
    }

    private JPanel createSacarPanel() {
        JPanel sacarPanel = new JPanel();
        sacarPanel.setLayout(new GridLayout(4, 2));

        JLabel numCSaqueLabel = new JLabel("Número da Conta:");
        numCSaqueField = new JTextField();
        JLabel numAgSaqueLabel = new JLabel("Número da Agência:");
        numAgSaqueField = new JTextField();
        JLabel valorSaqueLabel = new JLabel("Valor:");
        valorSaqueField = new JTextField();

        JButton sacarButton = new JButton("Sacar");
        sacarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numC = numCSaqueField.getText();
                String numAg = numAgSaqueField.getText();
                double valor = Double.parseDouble(valorSaqueField.getText());

                try {
                    bancoServices.sacarDeConta(numC, numAg, valor);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Saque realizado com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Essa conta é inválida, tente novamente com uma conta válida!");
                } catch (SaldoNaoDisponivelException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Não foi possível realizar o Saque! Seu saldo é menor que o valor que deseja sacar");
                }
            }
        });

        sacarPanel.add(numCSaqueLabel);
        sacarPanel.add(numCSaqueField);
        sacarPanel.add(numAgSaqueLabel);
        sacarPanel.add(numAgSaqueField);
        sacarPanel.add(valorSaqueLabel);
        sacarPanel.add(valorSaqueField);
        sacarPanel.add(new JLabel());
        sacarPanel.add(sacarButton);

        return sacarPanel;
    }

    private JPanel createDepositarPanel() {
        JPanel depositarPanel = new JPanel();
        depositarPanel.setLayout(new GridLayout(4, 2));

        JLabel numCDepositoLabel = new JLabel("Número da Conta:");
        numCDepositoField = new JTextField();
        JLabel numAgDepositoLabel = new JLabel("Número da Agência:");
        numAgDepositoField = new JTextField();
        JLabel valorDepositoLabel = new JLabel("Valor:");
        valorDepositoField = new JTextField();

        JButton depositarButton = new JButton("Depositar");
        depositarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numC = numCDepositoField.getText();
                String numAg = numAgDepositoField.getText();
                double valor = Double.parseDouble(valorDepositoField.getText());

                try {
                    bancoServices.depositarEmConta(numC, numAg, valor);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Depósito realizado com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Essa conta é inválida, tente novamente com uma conta válida!");
                }
            }
        });

        depositarPanel.add(numCDepositoLabel);
        depositarPanel.add(numCDepositoField);
        depositarPanel.add(numAgDepositoLabel);
        depositarPanel.add(numAgDepositoField);
        depositarPanel.add(valorDepositoLabel);
        depositarPanel.add(valorDepositoField);
        depositarPanel.add(new JLabel());
        depositarPanel.add(depositarButton);

        return depositarPanel;
    }

    private JPanel createPesquisarContaPanel() {
        JPanel pesquisarContaPanel = new JPanel();
        pesquisarContaPanel.setLayout(new GridLayout(4, 2));

        JLabel cpfPesquisaLabel = new JLabel("CPF:");
        cpfPesquisaField = new JTextField();
        JLabel novoNomeLabel = new JLabel("Novo Nome:");
        novoNomeField = new JTextField();

        JButton pesquisarContaButton = new JButton("Pesquisar Conta");
        pesquisarContaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfPesquisaField.getText();
                try {
                    String nome = String.valueOf(bancoServices.pesquisarContasDoCliente(cpf));
                    JOptionPane.showMessageDialog(BancoGUI.this, "O nome associado a esse CPF é: " + nome);
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Não foi possível encontrar uma conta com esse CPF");
                }
            }
        });

        JButton atualizarNomeButton = new JButton("Atualizar Nome");
        atualizarNomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfPesquisaField.getText();
                String novoNome = novoNomeField.getText();
                try {
                    bancoServices.alterarNome(cpf, novoNome);
                    JOptionPane.showMessageDialog(BancoGUI.this, "Nome atualizado com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(BancoGUI.this, "Não foi possível encontrar uma conta com esse CPF");
                }
            }
        });

        pesquisarContaPanel.add(cpfPesquisaLabel);
        pesquisarContaPanel.add(cpfPesquisaField);
        pesquisarContaPanel.add(new JLabel());
        pesquisarContaPanel.add(pesquisarContaButton);
        pesquisarContaPanel.add(novoNomeLabel);
        pesquisarContaPanel.add(novoNomeField);
        pesquisarContaPanel.add(new JLabel());
        pesquisarContaPanel.add(atualizarNomeButton);

        return pesquisarContaPanel;
    }

    public static void main(String[] args) {
        ArrayList<Conta> contas = new ArrayList<>();
        BancoServices bancoServices = new BancoServices(contas);
        BancoGUI bancoGUI = new BancoGUI(bancoServices);
        bancoGUI.setVisible(true);
    }
}
