package src.DAO;

import src.models.DocumentoModelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class AdicionarDocumentosDAO {
    private static final String FILE_NAME = "documentos.dat";

    public void save(DocumentoModelo documento) {
        if (documento == null || documento.getId() <= 0) {
            System.err.println("Documento inválido!");
            return;
        }
        List<DocumentoModelo> documentos = getAll();
        boolean found = false;
        for (int i = 0; i < documentos.size(); i++) {
            if (documentos.get(i).getId() == documento.getId()) {
                documentos.set(i, documento);
                found = true;
                break;
            }
        }
        if (!found) {
            documentos.add(documento);
        }
        saveToFile(documentos);
    }
    
    
    public DocumentoModelo get(int id) {
        List<DocumentoModelo> documentos = getAll();
        for (DocumentoModelo documento : documentos) {
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }

    private void saveToFile(List<DocumentoModelo> documentos) {
        if (documentos == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(documentos);
            System.out.println("Documentos salvos com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
public List<DocumentoModelo> getAll() {
    List<DocumentoModelo> documentos = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
        documentos = (List<DocumentoModelo>) ois.readObject();
    } catch (IOException e) {
        if (!(e instanceof java.io.EOFException)) {
            e.printStackTrace(); // Apenas mostra exceções que não são fim de arquivo
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    return documentos;
}

}
