package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class CategoriaProdutoModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id;
    private StringBufferModelo categoriaProduto;


    public CategoriaProdutoModelo(int id, String categoriaProduto){
        this.id = id;
        this.categoriaProduto = new StringBufferModelo(categoriaProduto);
    }

    public int getId(){
        return id;
    }

    public String getCategoriaProduto(){
        return categoriaProduto.get();
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setCategoriaProduto(String categoriaProduto){
        this.categoriaProduto.set(categoriaProduto);
    }
}
