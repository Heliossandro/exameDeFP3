package src.pages.produto;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import SwingComponents.RegistGeneric;
import src.components.utils.StringBufferModelo;
import src.pages.CategoriaProduto.CategoriaProdutoModelo;

public class ProdutoModelo implements RegistGeneric {

    private int id, quantidadeEmEstoque;
    private double preco;
    private StringBufferModelo nome, marca, dataDeValidade, observacao, fornecedor ;
    private CategoriaProdutoModelo categoria;

    public ProdutoModelo() {
        this.id = 0;
        this.quantidadeEmEstoque = 0;
        this.preco = 0.0;
        nome = new StringBufferModelo("", 50);
        marca = new StringBufferModelo("", 50);
        dataDeValidade = new StringBufferModelo("", 20);
        observacao = new StringBufferModelo("", 100);
        fornecedor = new StringBufferModelo("", 50);
        categoria = new CategoriaProdutoModelo();
    }

    public ProdutoModelo(int id, String nome, String marca, int quantidadeEmEstoque, double preco,
                         String dataDeValidade, String fornecedor, CategoriaProdutoModelo categoria, String observacao) {
        this.id = id;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
        this.nome = new StringBufferModelo(nome, 50);
        this.marca = new StringBufferModelo(marca, 50);
        this.dataDeValidade = new StringBufferModelo(dataDeValidade, 20);
        this.observacao = new StringBufferModelo(observacao, 100);
        this.fornecedor = new StringBufferModelo(fornecedor, 50);
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome.toStringEliminatingSpaces();
    }

    public String getMarca() {
        return marca.toStringEliminatingSpaces();
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public String getDataDeValidade() {
        return dataDeValidade.toStringEliminatingSpaces();
    }

    public String getFornecedor() {
        return fornecedor.toStringEliminatingSpaces();
    }

    public CategoriaProdutoModelo getCategoria() {
        return categoria;
    }


    public String getObservacao() {
        return observacao.toStringEliminatingSpaces();
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setNome(String newNome) {
        this.nome = new StringBufferModelo(newNome, 50);
    }

    public void setMarca(String newMarca) {
        this.marca = new StringBufferModelo(newMarca, 50);
    }

    public void setQuantidadeEmEstoque(int newQuantidadeEmEstoque) {
        this.quantidadeEmEstoque = newQuantidadeEmEstoque;
    }

    public void setPreco(double newPreco) {
        this.preco = newPreco;
    }

    public void setDataDeValidade(String newDataDeValidade) {
        this.dataDeValidade = new StringBufferModelo(newDataDeValidade, 20);
    }

    public void setFornecedor(String newFornecedor) {
        this.fornecedor = new StringBufferModelo(newFornecedor, 50);
    }

    public void setCategoria(CategoriaProdutoModelo newCategoria) {
        this.categoria = newCategoria;
    }

    public void setObservacao(String newObservacao) {
        this.observacao = new StringBufferModelo(newObservacao, 100);
    }

    @Override
    public String toString() {
        return "Dados do Produto\n\n" +
               "ID: " + getId() + "\n" +
               "Nome: " + getNome() + "\n" +
               "Marca: " + getMarca() + "\n" +
               "Quantidade em estoque: " + getQuantidadeEmEstoque() + "\n" +
               "Preço: " + getPreco() + "\n" +
               "Data de validade: " + getDataDeValidade() + "\n" +
               "Fornecedor: " + getFornecedor() + "\n" +
               "Categoria: " + getCategoria().getNome() + "\n" +
               "Observação: " + getObservacao() + "\n";
    }

    public long sizeof() {
        return (50 + 50 + 50 + 50) * 2 +  // dataVenda (50) + metodoDePagamento (50) -> cada caractere 2 bytes
               4 +  // id (int)
               4 +  // quantidade (int)
               4; // total (float)
    }
    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            nome.write(stream);
            marca.write(stream);
            stream.writeInt(quantidadeEmEstoque);
            stream.writeDouble(preco);
            dataDeValidade.write(stream);
            fornecedor.write(stream);
            stream.writeInt(categoria.getId());
            observacao.write(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            nome.read(stream);
            marca.read(stream);
            quantidadeEmEstoque = stream.readInt();
            preco = stream.readDouble();
            dataDeValidade.read(stream);
            fornecedor.read(stream);
            int categoriaId = stream.readInt();
        
            // Buscar o nome da categoria pelo ID
            String categoriaNome = CategoriaProdutoModelo.getNomePorId(categoriaId);
            categoria = new CategoriaProdutoModelo(categoriaId, categoriaNome);
            observacao.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void salvar() {
        new ProdutoFile().salvarDados(this);
        ProdutosPNode node = new ProdutosPNode(this);
        node.save();
    }
}  
