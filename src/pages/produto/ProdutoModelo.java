package src.pages.produto;

import java.io.IOException;
import java.io.RandomAccessFile;

import SwingComponents.DataModelo;
import SwingComponents.RegistGeneric;
import src.components.utils.StringBufferModelo;
import src.pages.CategoriaProduto.CategoriaProdutoModelo;

public class ProdutoModelo implements RegistGeneric {

    private int id, quantidadeEmEstoque;
    private double preco;
    private StringBufferModelo nome, marca, observacao, fornecedor;
    private DataModelo data;
    private CategoriaProdutoModelo categoria;

    public ProdutoModelo() {
        this.id = 0;
        this.quantidadeEmEstoque = 0;
        this.preco = 0.0;
        nome = new StringBufferModelo("", 50);
        marca = new StringBufferModelo("", 50);
        observacao = new StringBufferModelo("", 100);
        fornecedor = new StringBufferModelo("", 50);
        data = new DataModelo("11-11-1975");
        categoria = new CategoriaProdutoModelo();
    }

    public ProdutoModelo(int id, int quantidadeEmEstoque, double preco, String nome, String marca,  
                        String observacao,
                        String fornecedor, String data, CategoriaProdutoModelo categoria) {
        this.id = id;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
        this.nome = new StringBufferModelo(nome, 50);
        this.marca = new StringBufferModelo(marca, 50);
        this.observacao = new StringBufferModelo(observacao, 100);
        this.fornecedor = new StringBufferModelo(fornecedor, 50);
        this.data = new DataModelo(data);
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

    public String getFornecedor() {
        return fornecedor.toStringEliminatingSpaces();
    }

    public CategoriaProdutoModelo getCategoria() {
        return categoria;
    }

    public String getData() {
        return data.toString();
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

    public void setFornecedor(String newFornecedor) {
        this.fornecedor = new StringBufferModelo(newFornecedor, 50);
    }

    public void setCategoria(CategoriaProdutoModelo newCategoria) {
        this.categoria = newCategoria;
    }

    public void setData(String newData) {
        this.data = new DataModelo(newData);
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
               "Data de validade: " + getData() + "\n" +
               "Fornecedor: " + getFornecedor() + "\n" +
               "Categoria: " + getCategoria().getNome() + "\n" +
               "Observação: " + getObservacao() + "\n";
    }

    public long sizeof() throws IOException {
        return Integer.BYTES + // id
               Integer.BYTES + // quantidadeEmEstoque
               Double.BYTES + // preco
               nome.sizeof() + 
               marca.sizeof() + 
               observacao.sizeof() + 
               fornecedor.sizeof() + 
               data.sizeof() + // <- Aqui pode estar lançando IOException
               Integer.BYTES; // categoria ID
    }

    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            nome.write(stream);
            marca.write(stream);
            stream.writeInt(quantidadeEmEstoque);
            stream.writeDouble(preco);
            data.write(stream);
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
            data.read(stream);
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
