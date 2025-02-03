package src.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import src.components.utils.StringBufferModelo;
import src.pages.cliente.ClientePNode;
import SwingComponents.*;

public class ClienteModelo implements RegistGeneric{

    private int id;
    private StringBufferModelo nome, numTelefone, email, genero;

    public ClienteModelo(){
        id = 0;
		nome = new StringBufferModelo("", 50);
		numTelefone = new StringBufferModelo("",50);
		email = new StringBufferModelo("",50);
		genero = new StringBufferModelo("",20);
    }

    public ClienteModelo(int id, String nome, String numeTelefone, String email, String genero){
        this.id = id; 
        this.nome = new StringBufferModelo(nome,50);
        this.numTelefone = new StringBufferModelo(numeTelefone,50);
        this.email = new StringBufferModelo(email,50);
        this.genero = new StringBufferModelo(genero,20);
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome.toStringEliminatingSpaces();
    }

    public String getNumTelefone(){
        return numTelefone.toStringEliminatingSpaces();
    }

    public String getEmail(){
        return email.toStringEliminatingSpaces();
    }

    public String getGenero(){
        return genero.toStringEliminatingSpaces();
    }

    //metodos set
    public void setId(int newId){
        id = newId;
    }

    public void setNome(String newNome){
        nome = new StringBufferModelo(newNome, 50);
    }

    public void setNumTelefone(String NewNumTelfone){
        numTelefone = new StringBufferModelo(NewNumTelfone, 50);
    }

    public void setEmail(String newEmail){
        email = new StringBufferModelo(newEmail, 50);
    }

    public void setGenero(String newGenero){
        genero = new StringBufferModelo(newGenero, 20);
    }

    public long sizeof()
	{
		return 296;
	}

    public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(id);
			nome.write(stream);
			numTelefone.write(stream);
			email.write(stream);
			genero.write(stream);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	public void read(RandomAccessFile stream)
	{
		try
		{
			id = stream.readInt();
			nome.read(stream);
			numTelefone.read(stream);
			email.read(stream);
			genero.read(stream);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void salvar()
	{
		//faz-se a ligacao entre o modelo e o PNode
		ClientePNode node = new ClientePNode(this);
		node.save();
	}
}
