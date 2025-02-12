package src.pages.cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import src.components.utils.StringBufferModelo;
import SwingComponents.*;

public class ClienteModelo implements RegistGeneric {

    private int id;
    private StringBufferModelo nome, numTelefone, email;

    public ClienteModelo() {
        this.id = 0;
        nome = new StringBufferModelo("", 50);
        numTelefone = new StringBufferModelo("", 50);
        email = new StringBufferModelo("", 50);
    }

    public ClienteModelo(int id, String nome, String numeTelefone, String email) {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.numTelefone = new StringBufferModelo(numeTelefone, 50);
        this.email = new StringBufferModelo(email, 50);
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

    public String getEmail() {
        return email.toStringEliminatingSpaces();
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

    public void setEmail(String newEmail) {
        email = new StringBufferModelo(newEmail, 50);
    }

    public String toString()
	{
		String str = "Dados do Cliente no Modelo\n\n";
		
		str += "ID: " + getId() + "\n";
		str += "Nome: " + getNome() + "\n";
		str += "Numero de Telefone: " + getNumTelefone() + "\n";
		str += "Email: " + getEmail() + "\n";
		
		return str;
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
            email.write(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            nome.read(stream);
            numTelefone.read(stream);
            email.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     @SuppressWarnings("unchecked")
    public static List<ClienteModelo> listarClientes() {
        List<ClienteModelo> clientes = new ArrayList<>();
        File arquivo = new File("clientes.dat");

        if (!arquivo.exists()) {
            return clientes;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            clientes = (List<ClienteModelo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public static String getNomePorId(int id) {
        List<ClienteModelo> clientes = listarClientes(); // Método que retorna todas as clientes
        
        for (ClienteModelo cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente.getNome();
            }
        }
        
        return "Desconhecido"; // Caso não encontre a cliente
    }
    public void salvar() {
        //ele vai guardar tambem no ficheiro antigo para gerar apenas o id automatico
        new ClienteFile().salvarDados(this);
            // faz-se a ligação entre o modelo e o PNode
            ClientesPNode node = new ClientesPNode(this);
            node.save();
        }
}
