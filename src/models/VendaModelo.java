package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class VendaModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidade;
    private ProdutoModelo produto;
    private ClienteModelo cliente;
    private StringBufferModelo dataVenda, metodoDePagamento;
    private float total;

    public VendaModelo(int id,int quantidade, String dataVenda,ProdutoModelo produto,ClienteModelo cliente, String metodoDePagamento, float total){
        this.id = id; 
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.dataVenda = new StringBufferModelo(dataVenda,50);
        this.metodoDePagamento = new StringBufferModelo(metodoDePagamento,50);
        this.total = total;
    }

    public int getId(){
        return id;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public ProdutoModelo getProduto(){
        return produto;
    }

    public ClienteModelo getCliente(){
        return cliente;
    }

    public String getDataVenda(){
        return dataVenda.toStringEliminatingSpaces();
    }

    public String getMetodoDePagamento(){
        return metodoDePagamento.toStringEliminatingSpaces();
    }

    public float getTotal(){
        return total;
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public void setProduto(ProdutoModelo produto){
        this.produto = produto;
    }

    public void setCliente(ClienteModelo cliente){
        this.cliente = cliente;
    }

    public void setDataVenda(String NewDataVenda){
        dataVenda = new StringBufferModelo(NewDataVenda, 50);
    }

    public void setMetodoDePagamento(String NewNumTelfone){
        metodoDePagamento = new StringBufferModelo(NewNumTelfone,50);
    }

    public void setTotal(float total){
        this.total = total;
    }
}
