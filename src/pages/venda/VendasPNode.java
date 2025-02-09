package src.pages.venda;

import java.io.IOException;
import java.io.RandomAccessFile;
import src.components.utils.SaveWriteReadInteface;

public class VendasPNode extends VendasDadosTable implements SaveWriteReadInteface {
    private VendasPNode next, prev;
    private VendaModelo model;

    public VendasPNode(VendaModelo model) {
        super("vendas.DAT", 100);
        this.model = model;
        next = prev = null;
    }

    public VendasPNode() {
        model = new VendaModelo();
    }

    public String getKey() {
        return model.getProduto();
    }

    public boolean isEmptyNode() {
        return getKey().equalsIgnoreCase("");
    }

    public void write(RandomAccessFile stream) throws IOException {
        model.write(stream);
    }

    public void read(RandomAccessFile stream) throws IOException {
        model.read(stream);
    }

    public VendaModelo getModel() {
        return model;
    }

    public void setPrev(VendasPNode prev) {
        this.prev = prev;
    }

    public void setNext(VendasPNode next) {
        this.next = next;
    }

    public VendasPNode getNext() {
        return next;
    }

    public VendasPNode getPrev() {
        return prev;
    }

    public void save() {
        adicionarNovoVenda(this);
    }

    public static long sizeof() {
        VendasPNode node = new VendasPNode();
        try {
            return node.model.sizeof();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String toString() {
        return model.toString();
    }
}
