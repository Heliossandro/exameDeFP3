package src.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import src.pages.estoque.EstoqueModelo;

public class AdicionarEstoqueDAO {
    private static final String FILE_NAME = "estoque.dat";

    public void save(EstoqueModelo estoque) {
        if (estoque == null || estoque.getId() <= 0) {
            System.err.println("estoque inválido!");
            return;
        }
        List<EstoqueModelo> estoques = getAll();
        boolean found = false;
        for (int i = 0; i < estoques.size(); i++) {
            if (estoques.get(i).getId() == estoque.getId()) {
                estoques.set(i, estoque);
                found = true;
                break;
            }
        }
        if (!found) {
            estoques.add(estoque);
        }
        saveToFile(estoques);
    }
    
    
    public EstoqueModelo get(int id) {
        List<EstoqueModelo> estoques = getAll();
        for (EstoqueModelo estoque : estoques) {
            if (estoque.getId() == id) {
                return estoque;
            }
        }
        return null;
    }

    private void saveToFile(List<EstoqueModelo> estoques) {
        if (estoques == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(estoques);
            System.out.println("estoques salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
public List<EstoqueModelo> getAll() {
    List<EstoqueModelo> estoques = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
        estoques = (List<EstoqueModelo>) ois.readObject();
    } catch (IOException e) {
        if (!(e instanceof java.io.EOFException)) {
            e.printStackTrace(); // Apenas mostra exceções que não são fim de arquivo
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return estoques;
}
    
}
