package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class ClienteModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id;
    private StringBufferModelo nome, numTelefone, email, dataDeNascimento;
    private char genero;

    public ClienteModelo(int id, String nome, String numeTelefone, String email, String dataDeNascimento, char genero){
        this.id = id; 
        this.nome = new StringBufferModelo(nome);
        this.numTelefone = new StringBufferModelo(numeTelefone);
        this.email = new StringBufferModelo(email);
        this.dataDeNascimento = new StringBufferModelo(dataDeNascimento);
        this.genero = genero;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome.get();
    }

    public String getNumTelefone(){
        return numTelefone.get();
    }

    public String getEmail(){
        return email.get();
    }

    public String getDataDeNascimento(){
        return dataDeNascimento.get();
    }

    public char getGenero(){
        return genero;
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome.set(nome);
    }

    public void setNumTelefone(String numTelfone){
         this.numTelefone.set(numTelfone);
    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public void setDataDeNascimento(String dataDeNascimento){
        this.dataDeNascimento.set(dataDeNascimento);
    }

    public void setGenero(char genero){
        this.genero = genero;
    }
}
