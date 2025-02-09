package src.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import src.pages.CategoriaProduto.CategoriaProdutoModelo;


public class AdicionarCategoriaDAO {
    private static final String FILE_NAME = "categorias.dat";

    public void save(CategoriaProdutoModelo categoria) {
        if (categoria == null ||categoria.getId() <= 0) {
            System.err.println("categoria inválido!");
            return;
        }
        List<CategoriaProdutoModelo> categorias = getAll();
        boolean found = false;
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() ==categoria.getId()) {
                categorias.set(i,categoria);
                found = true;
                break;
            }
        }
        if (!found) {
            categorias.add(categoria);
        }
        saveToFile(categorias);
    }
    
    
    public CategoriaProdutoModelo get(int id) {
        List<CategoriaProdutoModelo> categorias = getAll();
        for (CategoriaProdutoModelo categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    private void saveToFile(List<CategoriaProdutoModelo> categorias) {
        if (categorias == null) return;
        System.out.println("Salvando categorias: " + categorias.size()); // Verifique o tamanho da lista
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(categorias);
            System.out.println("Categorias salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<CategoriaProdutoModelo> getAll() {
        List<CategoriaProdutoModelo> categorias = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            categorias = (List<CategoriaProdutoModelo>) ois.readObject();
            System.out.println("Categorias carregadas: " + categorias.size());
        } catch (IOException e) {
            if (!(e instanceof java.io.EOFException)) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }
    

}
