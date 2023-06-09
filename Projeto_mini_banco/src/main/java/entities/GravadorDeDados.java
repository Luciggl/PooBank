package entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GravadorDeDados {
    private String arquivoDeContas;

    public GravadorDeDados(String arquivoDeContas) {
        this.arquivoDeContas = arquivoDeContas;
    }

    public GravadorDeDados() {
        this("src/main/java/entities/arquivo.txt");
    }

    public void gravaContas(Contas contas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoDeContas))) {
            oos.writeObject(contas);
        } catch (IOException e) {
            throw new IOException("Erro ao gravar contas no arquivo!", e);
        }

    }

    public Contas lerContas() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoDeContas))) {
            return (Contas) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro ao ler contas do arquivo!", e);
        } catch (IOException e) {
            throw new IOException("Arquivo nao encontrado!", e);
        }
    }

}
