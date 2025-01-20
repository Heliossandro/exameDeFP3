package src.models;

import java.io.Serializable;
import src.components.utils.StringBufferModelo;

public class VendaModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidade;
    private ProdutoModelo produto;
    private ClienteModelo cliente;
    private StringBufferModelo dataVenda, metodoDePagamento;
    private double total;

    public VendaModelo(int id,int quantidade, String dataVenda,ProdutoModelo produto,ClienteModelo cliente, String metodoDePagamento, double total){
        this.id = id; 
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.dataVenda = new StringBufferModelo(dataVenda);
        this.metodoDePagamento = new StringBufferModelo(metodoDePagamento);
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
        return dataVenda.get();
    }

    public String getMetodoDePagamento(){
        return metodoDePagamento.get();
    }

    public double getTotal(){
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

    public void setDataVenda(String dataVenda){
        this.dataVenda.set(dataVenda);
    }

    public void setMetodoDePagamento(String numTelfone){
         this.metodoDePagamento.set(numTelfone);
    }

    public void setTotal(double total){
        this.total = total;
    }
}
