package src.models;

import java.io.Serializable;
import src.components.utils.*;

public class ProdutoModelo implements Serializable {
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidadeEmEstoque;
    private double preco;
    private StringBufferModelo nome, marca, dataDeValidade, observacao, fornecedor;
    private CategoriaProdutoModelo categoriaProduto;

    public ProdutoModelo(int id, int quantidadeEmEstoque, double preco, String nome, String marca, String dataDeValidade, String observacao, String fornecedor, CategoriaProdutoModelo categoriaProduto) {
        this.id = id;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
        this.nome = new StringBufferModelo(nome);
        this.marca = new StringBufferModelo(marca);
        this.dataDeValidade = new StringBufferModelo(dataDeValidade);
        this.observacao = new StringBufferModelo(observacao);
        this.fornecedor = new StringBufferModelo(fornecedor);
        this.categoriaProduto = categoriaProduto;
    }

    // Métodos get
    public int getId() {
        return id;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome.get();
    }

    public String getMarca() {
        return marca.get();
    }

    public String getDataDeValidade() {
        return dataDeValidade.get();
    }

    public String getObservacao() {
        return observacao.get();
    }

    public String getFornecedor() { // Corrigido de "getFonecidor" para "getFornecedor"
        return fornecedor.get();
    }

    public void setFornecedor(String fornecedor) { // Corrigido de "setFonecidor" para "setFornecedor"
    this.fornecedor.set(fornecedor);
}


    public CategoriaProdutoModelo getCategoriaProduto() {
        return categoriaProduto;
    }

    // Métodos set
    public void setId(int id) {
        this.id = id;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade.set(dataDeValidade);
    }

    public void setObservacao(String observacao) {
        this.observacao.set(observacao);
    }


    public void setCategoriaProduto(CategoriaProdutoModelo categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    // Método toString
    @Override
    public String toString() {
        return "Produto: " + nome + "\n" +
               ">> ID: " + id + "\n" +
               ">> Preço: " + preco + "\n" +
               ">> Marca: " + marca + "\n" +
               ">> Quantidade em Estoque: " + quantidadeEmEstoque + "\n" +
               ">> Categoria: " + categoriaProduto + "\n" +
               ">> Data de Validade: " + dataDeValidade + "\n" +
               ">> Observação: " + observacao + "\n" +
               ">> Fornecedor: " + fornecedor + "\n";
    }
}
