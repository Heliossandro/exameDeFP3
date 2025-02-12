package src.pages.venda;

import java.io.IOException;
import java.io.RandomAccessFile;
import SwingComponents.RegistGeneric;
import src.components.utils.StringBufferModelo;
import src.pages.produto.ProdutoFile;
import src.pages.produto.ProdutosPNode;

public class VendaModelo implements RegistGeneric {

    private int id, quantidade;
    private double total;  // Total não estava declarado
    private StringBufferModelo dataVenda, metodoDePagamento, produto, cliente;
    private double preco;

    public VendaModelo() {
        this.id = 0;
        this.quantidade = 0;
        this.produto = new StringBufferModelo(50);
        this.cliente = new StringBufferModelo(50);
        this.dataVenda = new StringBufferModelo(50);
        this.metodoDePagamento = new StringBufferModelo(50);
        this.preco = 0;
        this.total = 0;
    }

    public VendaModelo(int id, int quantidade, String dataVenda, String produto, String cliente, String metodoDePagamento, double preco, double total) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = new StringBufferModelo(produto, 50);
        this.cliente = new StringBufferModelo(cliente, 50);
        this.dataVenda = new StringBufferModelo(dataVenda, 50);
        this.metodoDePagamento = new StringBufferModelo(metodoDePagamento, 50);
        this.preco = preco;
        this.total = quantidade * preco; // Calculando total
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getProduto() {
        return produto.toStringEliminatingSpaces();
    }

    public String getCliente() {
        return cliente.toStringEliminatingSpaces();
    }

    public String getDataVenda() {
        return dataVenda.toStringEliminatingSpaces();
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento.toStringEliminatingSpaces();
    }

    public double getPreco(){
        return preco;
    }

    public double getTotal() {
        return total;
    }

    // Métodos set
    public void setId(int newId) {
        id = newId;
    }

    public void setQuantidade(int newQuantidade) {
        this.quantidade = newQuantidade;
        this.total = this.quantidade * this.preco; // Atualizar total
    }

    public void setProduto(String produto) {
        this.produto = new StringBufferModelo(produto, 50);
    }

    public void setCliente(String cliente) {
        this.cliente = new StringBufferModelo(cliente, 50);
    }

    public void setDataVenda(String newDataVenda) {
        dataVenda = new StringBufferModelo(newDataVenda, 50);
    }

    public void setMetodoDePagamento(String newMetodoPagamento) {
        metodoDePagamento = new StringBufferModelo(newMetodoPagamento, 50);
    }

    public void setPreco(double preco) {
        this.preco = preco;
    } 

    public void setTotal(double total) {
        this.total = total;
    }

    public long sizeof() {
        return (50 + 50 + 50 + 50) * 2 +  // dataVenda, metodoDePagamento, produto, cliente (cada caractere ocupa 2 bytes)
               4 +  // id (int)
               4 +  // quantidade (int)
               8 + 8; // total (double)
    }

    @Override
    public String toString() {
        return "Dados da Venda:\n" +
                "ID: " + id + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Produto: " + getProduto() + "\n" +
                "Cliente: " + getCliente() + "\n" +
                "Data da Venda: " + getDataVenda() + "\n" +
                "Método de Pagamento: " + getMetodoDePagamento() + "\n" +
                "Peco: " + getPreco() +
                "Total: " + getTotal() + "\n";
    }

    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            stream.writeInt(quantidade);
            produto.write(stream);
            cliente.write(stream);
            dataVenda.write(stream);
            metodoDePagamento.write(stream);
            stream.writeDouble(preco);
            stream.writeDouble(total);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            quantidade = stream.readInt();
            produto.read(stream);
            cliente.read(stream);
            dataVenda.read(stream);
            metodoDePagamento.read(stream);
            preco = stream.readDouble();
            total = stream.readDouble();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

     public void salvar() {
        new VendaFile().salvarDados(this);
        VendasPNode node = new VendasPNode(this);
        node.save();
    }
}
