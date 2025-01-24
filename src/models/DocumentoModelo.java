package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class DocumentoModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id;
    private StringBufferModelo nome;

    public DocumentoModelo(int id, String nome){
        this.id = id;
        this.nome = new StringBufferModelo(nome);
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome.get();
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome.set(nome);
    }
}
