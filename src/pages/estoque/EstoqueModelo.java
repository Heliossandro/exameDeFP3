package src.pages.estoque;

import java.io.Serializable;

import src.components.utils.StringBufferModelo;
import src.pages.produto.ProdutoModelo;
public class EstoqueModelo implements Serializable{

    private int id, quantidade;
    private ProdutoModelo produto;
    private StringBufferModelo dataAtualizacao;

    public EstoqueModelo(int id, int quantidade, ProdutoModelo produto, String dataAtualizacao){
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto; 
        this.dataAtualizacao = new StringBufferModelo(dataAtualizacao, 50);
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

    public String getdataAtualizacao(){
        return dataAtualizacao.toStringEliminatingSpaces();
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void getQuantidade(int qunatidade){
         this.quantidade = qunatidade;
    }

    public void getProduto(ProdutoModelo produto){
        this.produto = produto;
    }

    public void setDataAtualizacao(String NewDataAtualizacao){
        dataAtualizacao = new StringBufferModelo(NewDataAtualizacao, 50);
    }

    
}
