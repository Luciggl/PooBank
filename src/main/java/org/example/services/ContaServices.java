package org.example.services;

import org.example.entities.Conta;
import org.example.exception.ContaJaExisteException;
import org.example.exception.ContaListException;
import org.example.exception.ContaNaoExisteException;
import org.example.repository.ContaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ContaServices implements ContaRepository {
    public static HashMap<String, Conta> listContaControll = new HashMap<>();

    public static List<Conta> ContaList = new ArrayList<>();

    @Override
    public Conta findByCpf(String cpf) throws ContaNaoExisteException {
            return listContaControll.get(cpf);
    }

    @Override
    public void create(String keyCpf, Conta EntityConta) throws ContaJaExisteException, ContaNaoExisteException {
        listContaControll.put(keyCpf, EntityConta);

        //Salvar Lista de Contas
        ContaList.add(EntityConta);
    }

    @Override
    public void delete(String KeyCpf) throws ContaNaoExisteException {
        Conta conta = findByCpf(KeyCpf);
        if (conta == null) {
            throw new ContaNaoExisteException("A conta com o CPF " + KeyCpf + " não existe.");
        }
        listContaControll.remove(KeyCpf);
        ContaList.remove(conta);
    }

    @Override
    public ArrayList<Conta> pesquisarConta(String cpf) throws ContaNaoExisteException {
        ArrayList <Conta> contaCliente = new ArrayList<>();
        for(Conta c: ContaList){
            if(c.getCpf().equals(cpf)){
                contaCliente.add(c);
                return contaCliente;
            }
        }
        throw new ContaNaoExisteException("Esta conta é inválida, tente novamente com uma conta válida!");
    }

    @Override
    public ArrayList<Conta> getContas() {
        return null;
    }

    @Override
    public ArrayList<Conta> pesquisarContasComSaldoNegativo() {
        ArrayList<Conta> saldoNegativo = new ArrayList<>();
        for (Conta c : this.ContaList) {
            if (c.getSaldo() < 0) {
                saldoNegativo.add(c);
            }
        }
        return saldoNegativo;
    }

    @Override
    public double consultarSaldoDeConta(String cpf, String titular, int numConta) throws ContaNaoExisteException {
        if(existeConta(cpf)){
            double saldo = 0;
            for (Conta c : this.ContaList) {
                if (c.getCpf().equals(cpf) && c.getTitular().equals(titular) && Objects.equals(c.getNumConta(), numConta)) {
                    saldo = c.getSaldo();
                }
            }
            return saldo;
        }
        throw new ContaNaoExisteException("Não foi possível consultar o saldo de sua conta! Tente novamente com uma conta válida");
    }

    @Override
    public void transferir(String cpfO, int numContaO, String cpfD, int numContaD, double valor) throws ContaNaoExisteException {
        if(existeConta(cpfO) && existeConta(cpfD)){
            Conta contaO  = null;
            Conta contaD = null;
            for(Conta c: this.ContaList){
                if(c.getCpf().equals(cpfO) && Objects.equals(c.getNumConta(), numContaO)){
                    contaO = c;
                }

                if(c.getCpf().equals(cpfD) && Objects.equals(c.getNumConta(), numContaD)){
                    contaD = c;
                }
            }
            if(contaO!=null && contaD!= null && contaO!=contaD){
                contaO.debitar(valor);
                contaD.creditar(valor);
            }
        } throw new ContaNaoExisteException("Uma das contas ou as duas são(é) inválida(s)");
    }

    @Override
    public void sacarDeConta(String cpf, String titular, int numConta, double valor) throws ContaNaoExisteException {
        if(existeConta(cpf)){
            for(Conta c: ContaList){
                if(Objects.equals(c.getNumConta(), numConta) && (c.getTitular().equals(titular))){
                    c.debitar(valor);
                }
                throw new ContaNaoExisteException("Não foi possivel depositar nessa conta, tente novamente com uma conta valida");
            }
        }
    }

    @Override
    public void depositarEmConta(String cpf, String titular, int numConta, double valor) throws ContaNaoExisteException {
        if(existeConta(cpf)){
            for(Conta c: ContaList){
                if(Objects.equals(c.getNumConta(), numConta) && (c.getTitular().equals(titular))){
                    c.creditar(valor);
                }
                throw new ContaNaoExisteException("Não foi possivel depositar nessa conta, tente novamente com uma conta valida");
            }
        }
    }

    //Salvar dados
    @Override
    public void saveAllDB(String path) throws ContaListException {
        String Injectpath = path;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Injectpath))) {

            for (Conta line : ContaList) {
                bw.write(line.getTitular()+" ");
                bw.write(line.getCpf()+" ");
                bw.write(String.valueOf(line.getNumCelular())+" ");
                bw.write(line.getEmail()+" ");
                bw.write(line.getSenha()+" ");
                bw.write(String.valueOf(line.getNumConta())+" ");
                bw.write(String.valueOf(line.getSaldo())+" ");
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Recuperar dados
    @Override
    public void recoverDB(String path) {
        List<Conta> newReadList = new ArrayList<>();

        String Injectpath = path;
        try (BufferedReader br = new BufferedReader(new FileReader(Injectpath))){
            String line = br.readLine();
            while (line != null){
                String[] contaSplit = line.split(" ");

                String titular = contaSplit[0];
                String cpf = contaSplit[1];
                int numCelular = Integer.parseInt(contaSplit[2]);
                String email = contaSplit[3];
                String senha = contaSplit[4];
                int NumConta = Integer.parseInt(contaSplit[5]);
                double saldo = Double.parseDouble(contaSplit[6]);

                Conta buildConta = new Conta(titular,cpf,numCelular,email,senha,NumConta, saldo);

                newReadList.add(buildConta);
                listContaControll.put(cpf, buildConta);

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for(Conta readAll : newReadList) {
            System.out.println();
            System.out.println("MSG: (System recoverDB-ok)");
            System.out.println();
        }
    }



    public HashMap<String, Conta> getHashMap() {
        return listContaControll;
    }
    public String toString() {
        return "SistemaContaLista{" +
                "hashMap=" + listContaControll +
                '}'+"\n";
    }

    private boolean existeConta(String cpf) {
        for(Conta c: this.ContaList){
            if(c.getCpf().equals(cpf));
            return true;
        }
        return false;
    }
}
