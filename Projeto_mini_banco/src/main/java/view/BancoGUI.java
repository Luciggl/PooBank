package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import entities.Contas;
import exception.ContaJaExisteException;
import exception.ContaNaoExisteException;
import exception.SaldoInvalidoException;
import services.BancoServices;

public class BancoGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private BancoServices bancoServices;

    public BancoGUI(BancoServices bancoServices) {
        this.bancoServices = bancoServices;

        setTitle("Banco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 329);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Abrir Conta", createAbrirContaPanel());
        tabbedPane.addTab("Remover Conta", createRemoverContaPanel());
        tabbedPane.addTab("Abrir Conta Especial", createAbrirContaEspecialPanel());
        tabbedPane.addTab("Pesquisar Contas do Cliente", createPesquisarContasPanel());
        tabbedPane.addTab("Consultar Saldo de Conta", createConsultarSaldoPanel());
        tabbedPane.addTab("Transferir", createTransferirPanel());
        tabbedPane.addTab("Sacar de Conta", createSacarPanel());
        tabbedPane.addTab("Depositar em Conta", createDepositarPanel());
        tabbedPane.addTab("Alterar Nome", createAlterarNomePanel());

        add(tabbedPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAbrirContaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(10);
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(10);
        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JLabel saldoLabel = new JLabel("Saldo:");
        JTextField saldoField = new JTextField(10);
        JButton abrirContaButton = new JButton("Abrir Conta");

        abrirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());
                double saldo = Double.parseDouble(saldoField.getText());

                try {
                    bancoServices.abrirConta(nome, cpf, numC, numAg, saldo);
                    JOptionPane.showMessageDialog(null, "Conta aberta com sucesso!");
                } catch (ContaJaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(saldoLabel);
        panel.add(saldoField);
        panel.add(abrirContaButton);

        return panel;
    }

    private JPanel createRemoverContaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JButton removerContaButton = new JButton("Remover Conta");

        removerContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());

                try {
                    bancoServices.removerConta(numC, numAg);
                    JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(removerContaButton);

        return panel;
    }

    private JPanel createAbrirContaEspecialPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(10);
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(10);
        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JLabel saldoLabel = new JLabel("Saldo:");
        JTextField saldoField = new JTextField(10);
        JLabel creditoLabel = new JLabel("Crédito:");
        JTextField creditoField = new JTextField(10);
        JButton abrirContaEspecialButton = new JButton("Abrir Conta Especial");

        abrirContaEspecialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());
                double saldo = Double.parseDouble(saldoField.getText());
                double credito = Double.parseDouble(creditoField.getText());

                try {
                    bancoServices.abrirContaEspecial(nome, cpf, numC, numAg, saldo, credito);
                    JOptionPane.showMessageDialog(null, "Conta especial aberta com sucesso!");
                } catch (ContaJaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(saldoLabel);
        panel.add(saldoField);
        panel.add(creditoLabel);
        panel.add(creditoField);
        panel.add(abrirContaEspecialButton);

        return panel;
    }

    private JPanel createPesquisarContasPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField(10);
        JButton pesquisarContasButton = new JButton("Pesquisar Contas");

        pesquisarContasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();

                try {
                    Contas conta = bancoServices.pesquisarContasDoCliente(cpf);
                    JOptionPane.showMessageDialog(null, "Conta encontrada: " + conta);
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(pesquisarContasButton);

        return panel;
    }

    private JPanel createConsultarSaldoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JButton consultarSaldoButton = new JButton("Consultar Saldo");

        consultarSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());

                try {
                    double saldo = bancoServices.consultarSaldoDeConta(numC, numAg);
                    JOptionPane.showMessageDialog(null, "Saldo da conta: " + saldo);
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(consultarSaldoButton);

        return panel;
    }

    private JPanel createTransferirPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCOrigemLabel = new JLabel("Número da Conta de Origem:");
        JTextField numCOrigemField = new JTextField(10);
        JLabel numAgOrigemLabel = new JLabel("Número da Agência de Origem:");
        JTextField numAgOrigemField = new JTextField(10);
        JLabel numCDestinoLabel = new JLabel("Número da Conta de Destino:");
        JTextField numCDestinoField = new JTextField(10);
        JLabel numAgDestinoLabel = new JLabel("Número da Agência de Destino:");
        JTextField numAgDestinoField = new JTextField(10);
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField(10);
        JButton transferirButton = new JButton("Transferir");

        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numCOrigem = Integer.parseInt(numCOrigemField.getText());
                int numAgOrigem = Integer.parseInt(numAgOrigemField.getText());
                int numCDestino = Integer.parseInt(numCDestinoField.getText());
                int numAgDestino = Integer.parseInt(numAgDestinoField.getText());
                double valor = Double.parseDouble(valorField.getText());

                try {
                    bancoServices.transferir(numCOrigem, numAgOrigem, numCDestino, numAgDestino, valor);
                    JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                } catch (ContaNaoExisteException | SaldoInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCOrigemLabel);
        panel.add(numCOrigemField);
        panel.add(numAgOrigemLabel);
        panel.add(numAgOrigemField);
        panel.add(numCDestinoLabel);
        panel.add(numCDestinoField);
        panel.add(numAgDestinoLabel);
        panel.add(numAgDestinoField);
        panel.add(valorLabel);
        panel.add(valorField);
        panel.add(transferirButton);

        return panel;
    }

    private JPanel createSacarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField(10);
        JButton sacarButton = new JButton("Sacar");

        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());
                double valor = Double.parseDouble(valorField.getText());

                try {
                    bancoServices.sacarDeConta(numC, numAg, valor);
                    JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                } catch (ContaNaoExisteException | SaldoInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(valorLabel);
        panel.add(valorField);
        panel.add(sacarButton);

        return panel;
    }

    private JPanel createDepositarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JLabel valorLabel = new JLabel("Valor:");
        JTextField valorField = new JTextField(10);
        JButton depositarButton = new JButton("Depositar");

        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numC = Integer.parseInt(numCField.getText());
                int numAg = Integer.parseInt(numAgField.getText());
                double valor = Double.parseDouble(valorField.getText());

                try {
                    bancoServices.depositarEmConta(numC, numAg, valor);
                    JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(valorLabel);
        panel.add(valorField);
        panel.add(depositarButton);

        return panel;
    }

    private JPanel createAlterarNomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel numCLabel = new JLabel("Número da Conta:");
        JTextField numCField = new JTextField(10);
        JLabel numAgLabel = new JLabel("Número da Agência:");
        JTextField numAgField = new JTextField(10);
        JLabel novoNomeLabel = new JLabel("Novo Nome:");
        JTextField novoNomeField = new JTextField(10);
        JButton alterarNomeButton = new JButton("Alterar Nome");

        alterarNomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = numCField.getText();
                String novoNome = novoNomeField.getText();

                try {
                    bancoServices.alterarNome(cpf, novoNome);
                    JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
                } catch (ContaNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        panel.add(numCLabel);
        panel.add(numCField);
        panel.add(numAgLabel);
        panel.add(numAgField);
        panel.add(novoNomeLabel);
        panel.add(novoNomeField);
        panel.add(alterarNomeButton);

        return panel;
    }

    public static void main(String[] args) {
        ArrayList<Contas> contas = new ArrayList<>();
        BancoServices bancoServices = new BancoServices(contas);
        JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
        new BancoGUI(bancoServices);
    }
}