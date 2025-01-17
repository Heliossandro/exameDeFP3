package src.models;

import java.io.Serializable;
import src.components.utils.*;
public class ProdutoModelo implements Serializable{
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidadeEmEstoque;
    private double preco;
    private StringBufferModelo nome, marca, categoria, dataDeValidade, observacao, fornecidor;

    public ProdutoModelo(int id, int quantidadeEmEstoque,double preco, String nome, String marca, String categoria, String dataDeValidade, String observacao, String fornecidor){
        this.id = id;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
        this.nome = new StringBufferModelo(nome);
        this.marca = new StringBufferModelo(marca);
        this.categoria = new StringBufferModelo(categoria);
        this.dataDeValidade = new StringBufferModelo(dataDeValidade);
        this.observacao = new StringBufferModelo(observacao);
        this.fornecidor = new StringBufferModelo(fornecidor);
    }

    public int getId(){
        return id;
    }

    public int getQuantidadeEmEstoque(){
        return quantidadeEmEstoque;
    }

    public double getPreco(){
        return preco;
    }

    public String getNome(){
        return nome.get();
    }

    public String getMarca(){
        return marca.get();
    }
    
    public String getCategoria(){
        return categoria.get();
    }
    
    public String getDataDeValidade(){
    return dataDeValidade.get();
    }

    public String getObservacao(){
        return observacao.get();
    }

    public String getFonecidor(){
        return fornecidor.get();
    }

    //metodos set
    public void setId(int id){
        this.id = id;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque){
         this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void setPreco(double preco){
         this.preco = preco;
    }

    public void setNome(String nome){
        this.nome.set(nome);
    }

    public void setMarca(String marca){
         this.marca.set(marca);
    }
    
    public void setCategoria(String categoria){
         this.categoria.set(categoria);
    }
    
    public void setDataDeValidade(String dataDeValidade){
     this.dataDeValidade.set(dataDeValidade);
    }

    public void setObservacao(String observacao){
         this.observacao.set(observacao);
    }

    public void setFonecidor(String fornecidor){
         this.fornecidor.set(fornecidor);
    }

 public String toString(){
        return "Produto: "+ nome +"\n"+
               ">> ID: "+ id +"\n"+
               ">> preço: "+ preco +"\n"+
               ">> marca: "+ marca +"\n"+
               ">> marca: "+ marca +"\n"+
               ">> categoria: "+ categoria +"\n"+
               ">> data de validade: "+ dataDeValidade +"\n"+
               ">> fornecidor: "+ fornecidor +"\n";
    }
}
