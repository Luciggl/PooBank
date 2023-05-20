import entities.Account;
import exception.AccountAlreadyExistsException;
import exception.AccountDoesNotExistException;
import services.BankServices;
import services.RecorderAccount;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        BankServices myBank = null;
        RecorderAccount recorder = new RecorderAccount();
        //JOptionPane.showMessageDialog(null, "A tarefa falhou com exito");
        try {
            ArrayList<Account> accountsRecovered;
            accountsRecovered = recorder.readAccount();
            JOptionPane.showMessageDialog(null, "Contas Recuperadas Com Sucesso!");
            myBank = new BankServices(accountsRecovered);
        } catch (IOException e) {
            myBank = new BankServices(new ArrayList<>());
            JOptionPane.showMessageDialog(null, "Sistema iniciado sem dados");
            JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados. Detalhe do erro:" + e.getMessage());
        }
        boolean start = true;
        while (start) {
            String option;
            option = JOptionPane.showInputDialog(null,
                    """
                            1. Abrir conta\s
                            2. Pesquisar conta
                            3. Pesquisar contas com saldo negativo
                            4. Consultar saldo
                            5. Saque
                            6. Deposito
                            7. Transferir dinheiro
                            8. Salvar
                            9. Remover Conta
                            10. Sair""");

            if (option.equals("1")) {
                String optionAccount = JOptionPane.showInputDialog(null,
                        """
                                1. Conta corrente
                                2. Conta Polpança
                                3. Conta Empresarial
                                """);
                if (optionAccount.equals("1")) {
                    int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                    String holder = JOptionPane.showInputDialog(null, "Digite o nome do Portador da Conta");
                    double balance = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Deposito inicial"));

                    try {
                        myBank.openAccount(number, holder, balance);
                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    } catch (AccountAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }

                }
                if (optionAccount.equals("2")) {
                    int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                    String holder = JOptionPane.showInputDialog(null, "Digite o nome do Portador da Conta");
                    double balance = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Deposito inicial"));
                    double interestRate = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a taxa de juros!"));

                    try {
                        myBank.openSavingAccount(number, holder, balance, interestRate);
                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    } catch (AccountAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (optionAccount.equals("3")) {
                    int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                    String holder = JOptionPane.showInputDialog(null, "Digite o nome do Portador da Conta");
                    double balance = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o Deposito inicial"));
                    double loanLimit = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a taxa de credito!"));

                    try {
                        myBank.openSavingAccount(number, holder, balance, loanLimit);
                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                    } catch (AccountAlreadyExistsException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            if(option.equals("2")){
                int NumberAccount = Integer.parseInt(JOptionPane.showInputDialog(null,"Diigite o numero da conta a ser pesquisada"));
                    try {
                        JOptionPane.showMessageDialog(null,
                        myBank.searchAccount(NumberAccount));
                    } catch (AccountDoesNotExistException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        continue;
                    }

            }
            if(option.equals("3")){
                ArrayList<Account> NegativeAccount = myBank.searchAccountsWithNegativeBalance();
                for (Account c : NegativeAccount) {
                    JOptionPane.showMessageDialog(null, c.toString());
                }
            }
            if(option.equals("4")){
                int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                String holder = JOptionPane.showInputDialog(null, "Digite o nome do titular da conta");
                try {
                    myBank.consultAccountBalance(number,holder);
                    JOptionPane.showMessageDialog(null,
                            myBank.consultAccountBalance(number, holder));
                } catch (AccountDoesNotExistException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            if(option.equals("5")){
                boolean Validvalue = true;
                int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da Conta:"));
                String holder = JOptionPane.showInputDialog(null, "Digite o nome do Titular da conta");
                while (Validvalue) {
                    try {
                        double value = Double.parseDouble(
                                JOptionPane.showInputDialog(null, "Digite o valor que deseja sacar")
                        );
                        try {
                            myBank.withdraw(number, holder, value);
                            JOptionPane.showMessageDialog(null, "Seu salde atual é de:"
                            + myBank.consultAccountBalance(number,holder));
                        } catch (AccountDoesNotExistException e) {
                            throw new RuntimeException(e);
                        }
                        Validvalue = false;
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Erro! Digite apenas numeros aqui. Erro do tipo:"
                        + e.getMessage());
                        continue;
                    }
                }
            }
            if (option.equals("6")) {
                boolean ValidValue = true;
                int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                String holder = JOptionPane.showInputDialog(null, "Digite o nome do titular da conta");
                while (ValidValue) {
                    try {
                        double value = Double.parseDouble(
                                JOptionPane.showInputDialog(null, "Digite o valor a ser depositado"));
                        try {
                            myBank.depositInAccount(number, holder, value);
                            JOptionPane.showMessageDialog(null, "Seu saldo atual é de: "
                                    + myBank.consultAccountBalance(number, holder));
                        } catch (AccountDoesNotExistException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        ValidValue = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Erro! Digite apenas números aqui. Tipo de Erro: "
                                        + e.getMessage());
                        continue;
                    }
                }
            }
            if (option.equals("7")) {
                boolean ValidValue = true;
                int numberAcc1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o nomero da conta que dejesa transferir: "));
                String holder1 = JOptionPane.showInputDialog(null, "Digite o nome do titular da Conta: ");
                int numberAcc2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o nomero da conta que dejesa receber: "));
                String holder2 = JOptionPane.showInputDialog(null, "Digite o nome do titular da Conta: ");
                while (ValidValue) {
                    try {
                        double Value = Double.parseDouble(
                                JOptionPane.showInputDialog(null,
                                        "Digite o valor a ser Transferido"));
                        try {
                            myBank.transfer(numberAcc1, holder1, numberAcc2, holder2, Value);
                        } catch (AccountDoesNotExistException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        JOptionPane.showMessageDialog(null, "Transfêrencia realizada com sucesso!");
                        ValidValue = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Erro! Digite apenas números aqui. Tipo de Erro: "
                                        + e.getMessage());
                        continue;
                    }
                }
            }
            if(option.equals("8")){
                try {
                    recorder.RecordAccounts(myBank.getAccount());
                    JOptionPane.showMessageDialog(null, "Dados Gravados com sucesso!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro! tente novamente");
                }
            }
            if(option.equals("9")){
                try {
                    int number = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o numero da conta"));
                    myBank.removeAccount(number);
                    JOptionPane.showMessageDialog(null,"Conta removida com sucesso!");
                } catch (AccountDoesNotExistException e) {
                    JOptionPane.showMessageDialog(null, "Está conta não é valida ou não existe");
                }
            }
            if(option.equals("10")){
                JOptionPane.showMessageDialog(null, "Volte sempre!");
                start = false;
            } else {
                JOptionPane.showMessageDialog(null,"Operação invalida tente novamente");
            }
        }
    }
}
