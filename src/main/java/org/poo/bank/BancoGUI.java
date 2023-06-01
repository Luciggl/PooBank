package org.poo.bank;

import org.poo.bank.entities.Conta;
import org.poo.bank.services.BancoServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BancoGUI extends JFrame {
    private JPanel panelButtons;
    private JButton buttonAbrirConta;
    private JButton buttonRemoverConta;
    private JButton buttonConsultarSaldo;
    private JButton buttonSacar;
    private JButton buttonDepositar;
    private JButton buttonTransferir;
    private JButton buttonPesquisarContas;
    private JButton buttonAlterarNome;

    private JPanel panelAbrirConta;
    private JLabel labelNome;
    private JTextField textFieldNome;
    private JLabel labelCPF;
    private JTextField textFieldCPF;
    private JLabel labelNumConta;
    private JTextField textFieldNumConta;
    private JLabel labelNumAgencia;
    private JTextField textFieldNumAgencia;
    private JLabel labelSaldo;
    private JTextField textFieldSaldo;
    private JButton buttonSalvar;

    private JPanel panelRemoverConta;
    private JLabel labelNumContaRemover;
    private JTextField textFieldNumContaRemover;
    private JLabel labelNumAgenciaRemover;
    private JTextField textFieldNumAgenciaRemover;
    private JButton buttonRemover;

    private JPanel panelConsultarSaldo;
    private JLabel labelNumContaSaldo;
    private JTextField textFieldNumContaSaldo;
    private JLabel labelNumAgenciaSaldo;
    private JTextField textFieldNumAgenciaSaldo;
    private JButton buttonConsultar;

    private JPanel panelSacar;
    private JLabel labelNumContaSacar;
    private JTextField textFieldNumContaSacar;
    private JLabel labelNumAgenciaSacar;
    private JTextField textFieldNumAgenciaSacar;
    private JLabel labelValorSacar;
    private JTextField textFieldValorSacar;
    private JButton buttonSacarValor;

    private JPanel panelDepositar;
    private JLabel labelNumContaDepositar;
    private JTextField textFieldNumContaDepositar;
    private JLabel labelNumAgenciaDepositar;
    private JTextField textFieldNumAgenciaDepositar;
    private JLabel labelValorDepositar;
    private JTextField textFieldValorDepositar;
    private JButton buttonDepositarValor;

    private JPanel panelTransferir;
    private JLabel labelNumContaOrigem;
    private JTextField textFieldNumContaOrigem;
    private JLabel labelNumAgenciaOrigem;
    private JTextField textFieldNumAgenciaOrigem;
    private JLabel labelNumContaDestino;
    private JTextField textFieldNumContaDestino;
    private JLabel labelNumAgenciaDestino;
    private JTextField textFieldNumAgenciaDestino;
    private JLabel labelValorTransferir;
    private JTextField textFieldValorTransferir;
    private JButton buttonTransferirValor;

    private JPanel panelPesquisarContas;
    private JLabel labelCPFPesquisarContas;
    private JTextField textFieldCPFPesquisarContas;
    private JButton buttonPesquisarConta;

    private JPanel panelAlterarNome;
    private JLabel labelCPFAutenticacao;
    private JTextField textFieldCPFAutenticacao;
    private JLabel labelNomeNovo;
    private JTextField textFieldNomeNovo;
    private JButton ButtonAlterarNome;

    private BancoServices bancoServices;

    public BancoGUI(BancoServices bancoServices) {
        this.bancoServices = bancoServices;

        setTitle("Banco GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4, 2));

        buttonAbrirConta = new JButton("Abrir Conta");
        buttonAbrirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelAbrirConta);
            }
        });

        buttonRemoverConta = new JButton("Remover Conta");
        buttonRemoverConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelRemoverConta);
            }
        });

        buttonConsultarSaldo = new JButton("Consultar Saldo");
        buttonConsultarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelConsultarSaldo);
            }
        });

        buttonSacar = new JButton("Sacar");
        buttonSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelSacar);
            }
        });

        buttonDepositar = new JButton("Depositar");
        buttonDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelDepositar);
            }
        });

        buttonTransferir = new JButton("Transferir");
        buttonTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelTransferir);
            }
        });

        buttonPesquisarContas = new JButton("Pesquisar Contas");
        buttonPesquisarContas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelPesquisarContas);
            }
        });

        buttonAlterarNome = new JButton("Alterar Nome");
        buttonAlterarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(panelAlterarNome);
            }
        });

        panelButtons.add(buttonAbrirConta);
        panelButtons.add(buttonRemoverConta);
        panelButtons.add(buttonConsultarSaldo);
        panelButtons.add(buttonSacar);
        panelButtons.add(buttonDepositar);
        panelButtons.add(buttonTransferir);
        panelButtons.add(buttonPesquisarContas);
        panelButtons.add(buttonAlterarNome);

        panelAbrirConta = new JPanel();
        panelAbrirConta.setLayout(new GridLayout(6, 2));

        labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField();
        labelCPF = new JLabel("CPF:");
        textFieldCPF = new JTextField();
        labelNumConta = new JLabel("Número da Conta:");
        textFieldNumConta = new JTextField();
        labelNumAgencia = new JLabel("Número da Agência:");
        textFieldNumAgencia = new JTextField();
        labelSaldo = new JLabel("Saldo Inicial:");
        textFieldSaldo = new JTextField();
        buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir uma nova conta com os dados fornecidos
                String nome = textFieldNome.getText();
                String cpf = textFieldCPF.getText();
                String numConta = textFieldNumConta.getText();
                String numAgencia = textFieldNumAgencia.getText();
                double saldo = Double.parseDouble(textFieldSaldo.getText());
                // Lógica para abrir uma nova conta
                JOptionPane.showMessageDialog(null, "Conta aberta com sucesso!");
            }
        });

        panelAbrirConta.add(labelNome);
        panelAbrirConta.add(textFieldNome);
        panelAbrirConta.add(labelCPF);
        panelAbrirConta.add(textFieldCPF);
        panelAbrirConta.add(labelNumConta);
        panelAbrirConta.add(textFieldNumConta);
        panelAbrirConta.add(labelNumAgencia);
        panelAbrirConta.add(textFieldNumAgencia);
        panelAbrirConta.add(labelSaldo);
        panelAbrirConta.add(textFieldSaldo);
        panelAbrirConta.add(new JLabel());
        panelAbrirConta.add(buttonSalvar);

        panelRemoverConta = new JPanel();
        panelRemoverConta.setLayout(new GridLayout(3, 2));

        labelNumContaRemover = new JLabel("Número da Conta:");
        textFieldNumContaRemover = new JTextField();
        labelNumAgenciaRemover = new JLabel("Número da Agência:");
        textFieldNumAgenciaRemover = new JTextField();
        buttonRemover = new JButton("Remover");
        buttonRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para remover a conta com os dados fornecidos
                String numConta = textFieldNumContaRemover.getText();
                String numAgencia = textFieldNumAgenciaRemover.getText();
                // Lógica para remover a conta
                JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");
            }
        });

        panelRemoverConta.add(labelNumContaRemover);
        panelRemoverConta.add(textFieldNumContaRemover);
        panelRemoverConta.add(labelNumAgenciaRemover);
        panelRemoverConta.add(textFieldNumAgenciaRemover);
        panelRemoverConta.add(new JLabel());
        panelRemoverConta.add(buttonRemover);

        panelConsultarSaldo = new JPanel();
        panelConsultarSaldo.setLayout(new GridLayout(3, 2));

        labelNumContaSaldo = new JLabel("Número da Conta:");
        textFieldNumContaSaldo = new JTextField();
        labelNumAgenciaSaldo = new JLabel("Número da Agência:");
        textFieldNumAgenciaSaldo = new JTextField();
        buttonConsultar = new JButton("Consultar");
        buttonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar o saldo da conta com os dados fornecidos
                String numConta = textFieldNumContaSaldo.getText();
                String numAgencia = textFieldNumAgenciaSaldo.getText();
                // Lógica para consultar o saldo da conta
                double saldo = 0; // Coloque o valor do saldo consultado
                JOptionPane.showMessageDialog(null, "Saldo da conta: " + saldo);
            }
        });

        panelConsultarSaldo.add(labelNumContaSaldo);
        panelConsultarSaldo.add(textFieldNumContaSaldo);
        panelConsultarSaldo.add(labelNumAgenciaSaldo);
        panelConsultarSaldo.add(textFieldNumAgenciaSaldo);
        panelConsultarSaldo.add(new JLabel());
        panelConsultarSaldo.add(buttonConsultar);

        panelSacar = new JPanel();
        panelSacar.setLayout(new GridLayout(4, 2));

        labelNumContaSacar = new JLabel("Número da Conta:");
        textFieldNumContaSacar = new JTextField();
        labelNumAgenciaSacar = new JLabel("Número da Agência:");
        textFieldNumAgenciaSacar = new JTextField();
        labelValorSacar = new JLabel("Valor do Saque:");
        textFieldValorSacar = new JTextField();
        buttonSacarValor = new JButton("Sacar");
        buttonSacarValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para sacar o valor da conta com os dados fornecidos
                String numConta = textFieldNumContaSacar.getText();
                String numAgencia = textFieldNumAgenciaSacar.getText();
                double valor = Double.parseDouble(textFieldValorSacar.getText());
                // Lógica para sacar o valor da conta
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
            }
        });

        panelSacar.add(labelNumContaSacar);
        panelSacar.add(textFieldNumContaSacar);
        panelSacar.add(labelNumAgenciaSacar);
        panelSacar.add(textFieldNumAgenciaSacar);
        panelSacar.add(labelValorSacar);
        panelSacar.add(textFieldValorSacar);
        panelSacar.add(new JLabel());
        panelSacar.add(buttonSacarValor);

        panelDepositar = new JPanel();
        panelDepositar.setLayout(new GridLayout(4, 2));

        labelNumContaDepositar = new JLabel("Número da Conta:");
        textFieldNumContaDepositar = new JTextField();
        labelNumAgenciaDepositar = new JLabel("Número da Agência:");
        textFieldNumAgenciaDepositar = new JTextField();
        labelValorDepositar = new JLabel("Valor do Depósito:");
        textFieldValorDepositar = new JTextField();
        buttonDepositarValor = new JButton("Depositar");
        buttonDepositarValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para depositar o valor na conta com os dados fornecidos
                String numConta = textFieldNumContaDepositar.getText();
                String numAgencia = textFieldNumAgenciaDepositar.getText();
                double valor = Double.parseDouble(textFieldValorDepositar.getText());
                // Lógica para depositar o valor na conta
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
            }
        });

        panelDepositar.add(labelNumContaDepositar);
        panelDepositar.add(textFieldNumContaDepositar);
        panelDepositar.add(labelNumAgenciaDepositar);
        panelDepositar.add(textFieldNumAgenciaDepositar);
        panelDepositar.add(labelValorDepositar);
        panelDepositar.add(textFieldValorDepositar);
        panelDepositar.add(new JLabel());
        panelDepositar.add(buttonDepositarValor);

        panelTransferir = new JPanel();
        panelTransferir.setLayout(new GridLayout(6, 2));

        labelNumContaOrigem = new JLabel("Conta de Origem:");
        textFieldNumContaOrigem = new JTextField();
        labelNumAgenciaOrigem = new JLabel("Agência de Origem:");
        textFieldNumAgenciaOrigem = new JTextField();
        labelNumContaDestino = new JLabel("Conta de Destino:");
        textFieldNumContaDestino = new JTextField();
        labelNumAgenciaDestino = new JLabel("Agência de Destino:");
        textFieldNumAgenciaDestino = new JTextField();
        labelValorTransferir = new JLabel("Valor da Transferência:");
        textFieldValorTransferir = new JTextField();
        buttonTransferirValor = new JButton("Transferir");
        buttonTransferirValor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para transferir o valor entre as contas com os dados fornecidos
                String numContaOrigem = textFieldNumContaOrigem.getText();
                String numAgenciaOrigem = textFieldNumAgenciaOrigem.getText();
                String numContaDestino = textFieldNumContaDestino.getText();
                String numAgenciaDestino = textFieldNumAgenciaDestino.getText();
                double valor = Double.parseDouble(textFieldValorTransferir.getText());
                // Lógica para transferir o valor entre as contas
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
            }
        });

        panelTransferir.add(labelNumContaOrigem);
        panelTransferir.add(textFieldNumContaOrigem);
        panelTransferir.add(labelNumAgenciaOrigem);
        panelTransferir.add(textFieldNumAgenciaOrigem);
        panelTransferir.add(labelNumContaDestino);
        panelTransferir.add(textFieldNumContaDestino);
        panelTransferir.add(labelNumAgenciaDestino);
        panelTransferir.add(textFieldNumAgenciaDestino);
        panelTransferir.add(labelValorTransferir);
        panelTransferir.add(textFieldValorTransferir);
        panelTransferir.add(new JLabel());
        panelTransferir.add(buttonTransferirValor);

        panelPesquisarContas = new JPanel();
        panelPesquisarContas.setLayout(new GridLayout(2, 2));

        labelCPFPesquisarContas = new JLabel("CPF:");
        textFieldCPFPesquisarContas = new JTextField();
        buttonPesquisarContas = new JButton("Pesquisar");
        buttonPesquisarContas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para pesquisar as contas associadas a um CPF
                String cpf = textFieldCPFPesquisarContas.getText();
                // Lógica para pesquisar as contas associadas ao CPF
                // e exibir o resultado
                JOptionPane.showMessageDialog(null, "Contas encontradas: \nConta 1\nConta 2");
            }
        });

        panelPesquisarContas.add(labelCPFPesquisarContas);
        panelPesquisarContas.add(textFieldCPFPesquisarContas);
        panelPesquisarContas.add(new JLabel());
        panelPesquisarContas.add(buttonPesquisarContas);

        panelAlterarNome = new JPanel();
        panelAlterarNome.setLayout(new GridLayout(3, 2));

        labelCPFAutenticacao = new JLabel("CPF para Autenticação:");
        textFieldCPFAutenticacao = new JTextField();
        labelNomeNovo = new JLabel("Novo Nome:");
        textFieldNomeNovo = new JTextField();
        buttonAlterarNome = new JButton("Alterar Nome");
        buttonAlterarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para alterar o nome da conta com os dados fornecidos
                String cpf = textFieldCPFAutenticacao.getText();
                String novoNome = textFieldNomeNovo.getText();
                // Lógica para alterar o nome da conta
                JOptionPane.showMessageDialog(null, "Nome alterado com sucesso!");
            }
        });

        panelAlterarNome.add(labelCPFAutenticacao);
        panelAlterarNome.add(textFieldCPFAutenticacao);
        panelAlterarNome.add(labelNomeNovo);
        panelAlterarNome.add(textFieldNomeNovo);
        panelAlterarNome.add(new JLabel());
        panelAlterarNome.add(buttonAlterarNome);

        add(panelButtons, BorderLayout.NORTH);
        add(panelAbrirConta, BorderLayout.CENTER);
        add(panelRemoverConta, BorderLayout.CENTER);
        add(panelConsultarSaldo, BorderLayout.CENTER);
        add(panelSacar, BorderLayout.CENTER);
        add(panelDepositar, BorderLayout.CENTER);
        add(panelTransferir, BorderLayout.CENTER);
        add(panelPesquisarContas, BorderLayout.CENTER);
        add(panelAlterarNome, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void showPanel(JPanel panel) {
        panelButtons.setVisible(false);
        panelAbrirConta.setVisible(false);
        panelRemoverConta.setVisible(false);
        panelConsultarSaldo.setVisible(false);
        panelSacar.setVisible(false);
        panelDepositar.setVisible(false);
        panelTransferir.setVisible(false);
        panelPesquisarContas.setVisible(false);
        panelAlterarNome.setVisible(false);

        panel.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Conta> contas = new ArrayList<>();
        BancoServices bancoServices = new BancoServices(contas);
        BancoGUI bancoGUI = new BancoGUI(bancoServices);
    }
}
