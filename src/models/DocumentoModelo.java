package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class DocumentoModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id;
    private StringBufferModelo documento;


    public DocumentoModelo(int id, String documento){
        this.id = id;
        this.documento = new StringBufferModelo(documento);
    }

    public int getId(){
        return id;
    }

    public String getDocumento(){
        return documento.get();
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setDocumento(String documento){
        this.documento.set(documento);
    }
}
