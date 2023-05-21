package services;

import entities.Account;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class RecorderAccount {
    private String fileOfAccounts;

    public RecorderAccount(String fileOfAccounts) {
        this.fileOfAccounts = fileOfAccounts;
    }

    public RecorderAccount(){
        this("Account.txt");
    }

    public void RecordAccounts(ArrayList<Account> accounts) throws IOException{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.fileOfAccounts));
            for (Account c : accounts) {
                String lineOfAccount = c.setHolder() + "," + c.getNumber() + "," + c.getBalance();
                writer.write(lineOfAccount + "\n");
            }
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Account> readAccount() throws IOException {
        BufferedReader reader = null;
        ArrayList<Account> accountsRead = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(this.fileOfAccounts));
            String readLines = null;
            do {
                readLines = reader.readLine();
                if(readLines != null){
                    String [] words = readLines.split(",");
                    Account c = new Account(parseInt(words[0]), words[1], Double.parseDouble(words[2]));
                    accountsRead.add(c);
                }
            } while (readLines != null);
            } finally {
                if (reader != null) {
                    reader.close();
                }
        }
        return accountsRead;
    }

}
