package src.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import src.components.utils.*;

public class ProdutoModelo implements Serializable {
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidadeEmEstoque;
    private float preco;
    private StringBufferModelo nome, marca, dataDeValidade, observacao, fornecedor;
    private CategoriaProdutoModelo categoriaProduto;

    public ProdutoModelo(int id, int quantidadeEmEstoque, float preco, String nome, String marca, String dataDeValidade, String observacao, String fornecedor, CategoriaProdutoModelo categoriaProduto) {
        this.id = id;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
        this.nome = new StringBufferModelo(nome, 50);
        this.marca = new StringBufferModelo(marca, 50);
        this.dataDeValidade = new StringBufferModelo(dataDeValidade, 50);
        this.observacao = new StringBufferModelo(observacao, 50);
        this.fornecedor = new StringBufferModelo(fornecedor, 50);
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
        return nome.toStringEliminatingSpaces();
    }

    public String getMarca() {
        return marca.toStringEliminatingSpaces();
    }

    public String getDataDeValidade() {
        return dataDeValidade.toStringEliminatingSpaces();
    }

    public String getObservacao() {
        return observacao.toStringEliminatingSpaces();
    }

    public String getFornecedor() { // Corrigido de "getFonecidor" para "getFornecedor"
        return fornecedor.toStringEliminatingSpaces();
    }

    public void setFornecedor(String fornecedor) { // Corrigido de "setFonecidor" para "setFornecedor"
    this.fornecedor.toStringEliminatingSpaces();
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

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setNome(String newNome) {
        nome = new StringBufferModelo(newNome, 50);
    }

    public void setMarca(String NewMarca) {
        marca = new StringBufferModelo(NewMarca, 50);
    }

    public void setDataDeValidade(String NewDataDeValidade) {
        dataDeValidade = new StringBufferModelo(NewDataDeValidade, 50);
    }

    public void setObservacao(String NewObservacao) {
        observacao = new StringBufferModelo(NewObservacao, 50);
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

/*         public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            stream.writeInt(quantidadeEmEstoque);
            stream.writeFloat(preco);
            nome.write(stream);
            marca.write(stream);
            dataDeValidade.write(stream);
            observacao.write(stream);
            fornecedor.write(stream);
            categoriaProduto.write(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            quantidadeEmEstoque = stream.readInt();
            preco = stream.readFloat();
            nome.read(stream);
            marca.read(stream);
            dataDeValidade.read(stream);
            observacao.read(stream);
            fornecedor.read(stream);
            categoriaProduto.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } */

}
