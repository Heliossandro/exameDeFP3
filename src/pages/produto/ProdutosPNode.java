package src.pages.produto;

import java.io.IOException;
import java.io.RandomAccessFile;
import src.components.utils.SaveWriteReadInteface;

public class ProdutosPNode extends ProdutosDadosTable implements SaveWriteReadInteface {
    private ProdutosPNode next, prev;
    private ProdutoModelo model;

    public ProdutosPNode(ProdutoModelo model) {
        super("produtos.DAT", 100);
        this.model = model;
        next = prev = null;
    }

    public ProdutosPNode() {
        model = new ProdutoModelo();
    }

    public String getKey() {
        return model.getNome();
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

    public ProdutoModelo getModel() {
        return model;
    }

    public void setPrev(ProdutosPNode prev) {
        this.prev = prev;
    }

    public void setNext(ProdutosPNode next) {
        this.next = next;
    }

    public ProdutosPNode getNext() {
        return next;
    }

    public ProdutosPNode getPrev() {
        return prev;
    }

    public void save() {
        adicionarNovoProduto(this);
    }

    public static long sizeof() {
        ProdutosPNode node = new ProdutosPNode();
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
