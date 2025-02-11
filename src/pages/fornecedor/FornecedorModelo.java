package src.pages.fornecedor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import src.components.utils.StringBufferModelo;
import SwingComponents.*;

public class FornecedorModelo implements RegistGeneric {

    private int id;
    private StringBufferModelo nome, numTelefone, endereco;

    public FornecedorModelo() {
        this.id = 0;
        nome = new StringBufferModelo("", 50);
        numTelefone = new StringBufferModelo("", 50);
        endereco = new StringBufferModelo("", 50);
    }

    public FornecedorModelo(int id, String nome, String numeTelefone, String endereco) {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.numTelefone = new StringBufferModelo(numeTelefone, 50);
        this.endereco = new StringBufferModelo(endereco, 50);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome.toStringEliminatingSpaces();
    }

    public String getNumTelefone() {
        return numTelefone.toStringEliminatingSpaces();
    }

    public String getEndereco() {
        return endereco.toStringEliminatingSpaces();
    }

    // métodos set
    public void setId(int newId) {
        id = newId;
    }

    public void setNome(String newNome) {
        nome = new StringBufferModelo(newNome, 50);
    }

    public void setNumTelefone(String newNumTelefone) {
        numTelefone = new StringBufferModelo(newNumTelefone, 50);
    }

    public void setEndereco(String newEndereco) {
        endereco = new StringBufferModelo(newEndereco, 50);
    }

    public String toString()
	{
        return "Dados do Produto\n\n" +
               "ID: " + getId() + "\n" +
               "Nome: " + getNome() + "\n" +
               "Numero de telefone: "+ getNumTelefone() + "\n" +                "Endereço: " + getEndereco();
	}


    public long sizeof() {
        return 4  // int id
             + (50 * 2)  // nome
             + (50 * 2)  // numTelefone
             + (50 * 2); // genero (20 caracteres)
    }
    

    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            nome.write(stream);
            numTelefone.write(stream);
            endereco.write(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            nome.read(stream);
            numTelefone.read(stream);
            endereco.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     @SuppressWarnings("unchecked")
    public static List<FornecedorModelo> listarFornecedors() {
        List<FornecedorModelo> fornecedores = new ArrayList<>();
        File arquivo = new File("fornecedores.dat");

        if (!arquivo.exists()) {
            return fornecedores;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            fornecedores = (List<FornecedorModelo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fornecedores;
    }

    public static String getNomePorId(int id) {
        List<FornecedorModelo> Fornecedores = listarFornecedors(); // Método que retorna todas as Fornecedors
        
        for (FornecedorModelo fornecedor : Fornecedores) {
            if (fornecedor.getId() == id) {
                return fornecedor.getNome();
            }
        }
        
        return "Desconhecido"; // Caso não encontre a Fornecedor
    }
    public void salvar() {
        //ele vai guardar tambem no ficheiro antigo para gerar apenas o id automatico
        new FornecedorFile().salvarDados(this);
            // faz-se a ligação entre o modelo e o PNode
            FornecedoresPNode node = new FornecedoresPNode(this);
            node.save();
        }
}
