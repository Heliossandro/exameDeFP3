package src.pages.cliente;



import java.io.IOException;
import java.io.RandomAccessFile;
import src.components.utils.StringBufferModelo;
import SwingComponents.*;

public class ClienteModelo implements RegistGeneric {

    private int id;
    private StringBufferModelo nome, numTelefone, email, genero;

    public ClienteModelo() {
        this.id = 0;
        nome = new StringBufferModelo("", 50);
        numTelefone = new StringBufferModelo("", 50);
        email = new StringBufferModelo("", 50);
        genero = new StringBufferModelo("", 10);
    }

    public ClienteModelo(int id, String nome, String numeTelefone, String email, String genero) {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.numTelefone = new StringBufferModelo(numeTelefone, 50);
        this.email = new StringBufferModelo(email, 50);
        this.genero = new StringBufferModelo(genero, 10);
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

    public String getGenero() {
        return genero.toStringEliminatingSpaces();
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

    public void setGenero(String newGenero) {
        genero = new StringBufferModelo(newGenero, 10);
    }

    public String toString()
	{
		String str = "Dados do Cliente no Modelo\n\n";
		
		str += "ID: " + getId() + "\n";
		str += "Nome: " + getNome() + "\n";
		str += "Genero: " + getGenero() + "\n";
		str += "Numero de Telefone: " + getNumTelefone() + "\n";
		str += "Email: " + getEmail() + "\n";
		
		return str;
	}


    public long sizeof() {
        try
        {
            return (50+50+50+10)*2+4;// 324 bytes, qualquer coisa usa a formula
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            nome.write(stream);
            genero.write(stream);
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
            genero.read(stream); // a sequencia que colocas aqui tem que ser a mesma da funcao toString
            numTelefone.read(stream);
            email.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void salvar() {
	//ele vai guardar tambem no ficheiro antigo para gerar apenas o id automatico
	new ClienteFile().salvarDados(this);
        // faz-se a ligação entre o modelo e o PNode
        ClientesPNode node = new ClientesPNode(this);
        node.save();
    }
}
