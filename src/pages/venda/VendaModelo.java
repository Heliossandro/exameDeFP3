package src.pages.venda;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import src.components.utils.StringBufferModelo;
import src.pages.cliente.ClienteModelo;
import src.pages.produto.ProdutoModelo;

public class VendaModelo implements Serializable {
    private static final long serialVersionUID = 7915799766965423151L;

    private int id, quantidade;
    private ProdutoModelo produto;
    private ClienteModelo cliente;
    private StringBufferModelo dataVenda, metodoDePagamento;
    private float total;

    public VendaModelo(int id, int quantidade, String dataVenda, ProdutoModelo produto, ClienteModelo cliente, String metodoDePagamento, float total) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.dataVenda = new StringBufferModelo(dataVenda, 50);
        this.metodoDePagamento = new StringBufferModelo(metodoDePagamento, 50);
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ProdutoModelo getProduto() {
        return produto;
    }

    public ClienteModelo getCliente() {
        return cliente;
    }

    public String getDataVenda() {
        return dataVenda.toStringEliminatingSpaces();
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento.toStringEliminatingSpaces();
    }

    public float getTotal() {
        return total;
    }

    // métodos set
    public void setId(int newId) {
        id = newId;
    }

    public void setQuantidade(int NewQuantidade) {
        this.quantidade = NewQuantidade;
    }

    public void setProduto(ProdutoModelo produto) {
        this.produto = produto;
    }

    public void setCliente(ClienteModelo cliente) {
        this.cliente = cliente;
    }

    public void setDataVenda(String newDataVenda) {
        dataVenda = new StringBufferModelo(newDataVenda, 50);
    }

    public void setMetodoDePagamento(String newMetodoPagamento) {
        metodoDePagamento = new StringBufferModelo(newMetodoPagamento, 50);
    }

    public void setTotal(float total) {
        this.total = total;
    }
    public long sizeof() {
        return (50 + 50 + 50 + 50) * 2 +  // dataVenda (50) + metodoDePagamento (50) -> cada caractere 2 bytes
               4 +  // id (int)
               4 +  // quantidade (int)
               4; // total (float)
    }
    
    @Override
    public String toString() {
        return "Dados da Venda:\n" +
                "ID: " + id + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Produto: " + produto.getNome() + "\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Data da Venda: " + getDataVenda() + "\n" +
                "Método de Pagamento: " + getMetodoDePagamento() + "\n" +
                "Total: " + total + "\n";
    }

    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            stream.writeInt(quantidade);
            produto.write(stream);
            cliente.write(stream);
            dataVenda.write(stream);
            metodoDePagamento.write(stream);
            stream.writeFloat(total);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            quantidade = stream.readInt();
            produto = new ProdutoModelo();
            produto.read(stream);
            cliente = new ClienteModelo();
            cliente.read(stream);
            dataVenda.read(stream);
            metodoDePagamento.read(stream);
            total = stream.readFloat();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
