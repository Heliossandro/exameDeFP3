package src.pages.CategoriaProduto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProdutoModelo implements Serializable {
    private static final long serialVersionUID = 7915799766965423151L;

    private int id;
    private StringBufferModelo nome;

    public CategoriaProdutoModelo() {
        id = 0;
        nome = new StringBufferModelo("");
    }

    public CategoriaProdutoModelo(int id, String nome) {
        this.id = id;
        this.nome = new StringBufferModelo(nome);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    @SuppressWarnings("unchecked")
    public static List<CategoriaProdutoModelo> listarCategorias() {
        List<CategoriaProdutoModelo> categorias = new ArrayList<>();
        File arquivo = new File("categorias.dat");

        if (!arquivo.exists()) {
            return categorias;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            categorias = (List<CategoriaProdutoModelo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public static String getNomePorId(int id) {
        List<CategoriaProdutoModelo> categorias = listarCategorias(); // Método que retorna todas as categorias
        
        for (CategoriaProdutoModelo categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria.getNome();
            }
        }
        
        return "Desconhecido"; // Caso não encontre a categoria
    }
    
}
