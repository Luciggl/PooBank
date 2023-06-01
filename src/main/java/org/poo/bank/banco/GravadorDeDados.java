package org.poo.bank.banco;

import org.poo.bank.entities.Conta;

import java.io.*;
import java.util.ArrayList;

public class GravadorDeDados {
    private String arquivoDeContas;

    public GravadorDeDados(String arquivoDeContas) {
        this.arquivoDeContas = arquivoDeContas;
    }

    public GravadorDeDados() {
        this("Conta.dat");
    }

    public void gravaContas(ArrayList<Conta> contas) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.arquivoDeContas))){
            oos.writeObject(contas);
        } catch (IOException e){
            throw new IOException("Arquivo não encontrado!");
        }

    }

    public ArrayList<Conta> lerContas() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.arquivoDeContas))){
            return (ArrayList<Conta>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ocorreu um problema, tente novamente!",e);
        } catch (IOException e){
            throw new IOException("Arquivo não encontrado!");
        }
    }
}
