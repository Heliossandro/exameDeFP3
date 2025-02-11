package src.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import src.pages.CategoriaProduto.CategoriaProdutoModelo;

public class AdicionarCategoriaDAO {
    private static final String FILE_NAME = "categoriaProduto.dat";

    public void save(CategoriaProdutoModelo produto){
        List<CategoriaProdutoModelo> produtos = getAll();
        boolean found = false;
        for(int i = 0 ; i < produtos.size() ; i++){
            if(produtos.get(i).getId() == produto.getId()){
                produtos.set(i, produto);
                found = true;
                break;
            }
        }
        if(!found){
            produtos.add(produto);
        }
        saveToFile(produtos);
    } 
    
    public CategoriaProdutoModelo get(int id) {
        List<CategoriaProdutoModelo> produtos = getAll();
        for (CategoriaProdutoModelo produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    private void saveToFile(List<CategoriaProdutoModelo> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<CategoriaProdutoModelo> getAll() {
        List<CategoriaProdutoModelo> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            produtos = (List<CategoriaProdutoModelo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Arquivo não encontrado ou vazio
        }
        return produtos;
    } 
}
