package src.pages.cliente;

import src.components.utils.*;
import javax.swing.*;
import java.util.*;

public class ClienteDadosTable extends AbstractHashTableCoalashed{
    public ClienteDadosTable(String fileName, int tableSize){
        super(fileName, tableSize);
    }

    public ClienteDadosTable(){

    }
    
    public int calcularHashCodeRehashing(String key){
        return 0;
    }
    
    public int calcularHashCode(String key){
        return Math.abs(key.hashCode()) % tableSize;
    }

    public SaveWriteReadInteface getNode(int tablePosition) {
        ClientePNode node = (ClientePNode) getEmptyNode();
        long pos = getFilePosition(tablePosition);
    
        try {    
            stream.seek(pos);
            node.read(stream);
    
            if (!node.isEmptyNode()) {
                return node;
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Adicionado para identificar erros
            ShowMessage.displayMessage("[GetNode] Falha ao ler o arquivo " + fileName, "ERROR", true);
        }
    
        return null;
    }    
    
    public ClientePNode getNode(String key) {
        int tablePosition = calcularHashCode(key);
        ClientePNode tmp = (ClientePNode) getNode(tablePosition);
    
        while (tmp != null) { // Evita acessar null
            if (tmp.getKey().equalsIgnoreCase(key)) {
                return tmp;
            }
            tmp = tmp.getNext();
        }
        return null; // Se não encontrar, retorna null
    }

    public static boolean exists(String key) {
        ClienteDadosTable table = new ClienteDadosTable("clientes.DAT", 100);
        ClientePNode tmp = table.getNode(key);
    
        return (tmp != null && !tmp.isEmptyNode());
    }


 
    public static Vector<String> getAllNodes() {
        Vector<String> listaNodes = new Vector<>();
        ClienteDadosTable hashCliente = new ClienteDadosTable("clientes.DAT", 100);
        ClientePNode tmp = new ClientePNode();
    
        hashCliente.openFile();
    
        try {
            hashCliente.stream.seek(8);
            for (int i = 0; i < hashCliente.tableSize; i++) {
                tmp.read(hashCliente.stream);
    
                if (!tmp.isEmptyNode()) {
                    listaNodes.addElement(tmp.getKey());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Mostra o erro real no console
        }
        
        return listaNodes;
    }
    
//adiciona na tabela e depois no ficheiro
public void adicionarNovoCliente(ClientePNode node) {
    try {
        stream.seek(0);
        int nTableSize = stream.readInt();
        int nElements = stream.readInt();

        // Trata do overflow da tabela a 80%
        if ((nElements + 1) / (double) nTableSize >= 0.8) { // Mudança no cálculo de proporção
            ouverFlowFile(nTableSize, nElements);
        }
    } catch (Exception ex) {
        ShowMessage.displayMessage("Falha ao adicionar um cliente " + fileName, "ERROR", true);	
    }

    int posTabela = calcularHashCode(node.getKey());

    if (getNode(posTabela) == null) {
        adicionarNoFicheiro(node, posTabela);
    } else if (getNode(posTabela).getKey().equalsIgnoreCase(node.getKey())) {
        if (JOptionPane.showConfirmDialog(null, "Este Registo já existe. Deseja sobrepor?") == JOptionPane.YES_OPTION) {
            sobrePorRegisto(node, posTabela);
        }
    } else {
        adicionarNaListaColisoes(node, posTabela);
    }
}



// sobrepoem 1 registo
public void sobrePorRegisto(ClientePNode node, int posTabela)
{	
    ClientePNode no = (ClientePNode)getNode(posTabela);	
    
    node.setNext(no.getNext());
    
    node.setPrev(no.getPrev());	
    
    adicionarNoFicheiro(node, posTabela);
    
}

//segredo do projecto
//adiciona na lista de colisoes
public void adicionarNaListaColisoes(ClientePNode node, int lastColision) {
    ClientePNode no = (ClientePNode) getNode(lastColision);

    if (no.getNext() == null && lastColision != tableSize - 1) {
        no.setNext(node);
        node.setPrev(no);
        adicionarNoFicheiro(node, tableSize - 1);
    } else {
        ClientePNode tmp = no.getNext();

        for (int i = tableSize - 1; i >= 0; --i) {
            if (tmp == null || tmp.getNext() == null) { // Corrigida a condição de adição
                if (tmp != null) {
                    tmp.setNext(node);
                    node.setPrev(tmp);
                }
                adicionarNoFicheiro(node, i);
                return;
            } else {
                tmp = tmp.getNext();
            }
        }
    }
}

//calcula onde o fichiro deve se posicionar
// para escrever o proximo registo
 public long getFilePosition(int tablePosition)
 {
    return (2 * 4 + tablePosition * ClientePNode.sizeof());
 }


public SaveWriteReadInteface getEmptyNode()
{
    return new ClientePNode();
}

public static int getNextID()
{
    
    return 1;
}

public static void pesquisarClientesPorGenero(String generoProcurado)
{
    ClienteDadosTable hashCliente = new ClienteDadosTable("clientes.DAT",100);
    ClientePNode tmp = (ClientePNode)hashCliente.getEmptyNode();
    String output = "\n";
    
    try
    {
            hashCliente.openFile();
            
            hashCliente.stream.seek(8);
            
            for(int i = 0; i < hashCliente.tableSize;++i)
            {
                tmp.read(hashCliente.stream);
                
                if(!tmp.getKey().equals(""))
                {						
                    if (tmp.getModel().getGenero().equals(generoProcurado))
                    {
                        output += tmp.toString();
                        
                        output += "------------------------------\n";
                    }
                }						
            }
            
            JTextArea area = new JTextArea(40, 50);
            area.setText(output);
            area.setEditable(false);
            
            JOptionPane.showMessageDialog(null, new JScrollPane(area), "Listagem de clientes", JOptionPane.INFORMATION_MESSAGE);
            
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("excpcao no metodo Listar Alunos");
    }
    
}

/*Listar os dados dos alunos numa comboBox*/
public static void listarClientes()
{
    ClienteDadosTable hashCliente = new ClienteDadosTable("clientes.DAT",100);
    ClientePNode tmp = (ClientePNode)hashCliente.getEmptyNode();
    String output = "\n";
    
    try
    {
            hashCliente.openFile();
            
            hashCliente.stream.seek(8);
            
            for(int i = 0; i < hashCliente.tableSize;++i)
            {
                tmp.read(hashCliente.stream);
                
                if(!tmp.getKey().equals(""))
                {						
                       output += tmp.toString();
                    
                    output += "------------------------------\n";
                }						
            }
            
            JTextArea area = new JTextArea(40, 50);
            area.setText(output);
            area.setEditable(false);
            
            JOptionPane.showMessageDialog(null, new JScrollPane(area), "Listagem de clientes", JOptionPane.INFORMATION_MESSAGE);
            
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("excpcao no metodo Listar Alunos");
    }
    
}

public void reHashFile(int nTableSize) {
    try {
        // Mover o ponteiro para o início do arquivo
        stream.seek(0);

        int oldTableSize = stream.readInt();
        int oldElements = stream.readInt();

        // Novo tamanho da tabela (pode-se usar um número primo)
        int newTableSize = getNextPrime(nTableSize * 2);

        // Criar uma nova tabela temporária em memória
        ClientePNode[] tempNodes = new ClientePNode[oldElements];

        // Ler todos os registros existentes
        int index = 0;
        for (int i = 0; i < oldTableSize; i++) {
            SaveWriteReadInteface node = getNode(i);
            if (node != null) {
                tempNodes[index++] = (ClientePNode) node;
            }
        }

        // Limpar e reescrever a estrutura com o novo tamanho
        stream.setLength(0); // Apaga o conteúdo do arquivo
        stream.seek(0);
        stream.writeInt(newTableSize); // Novo tamanho da tabela
        stream.writeInt(0); // Reiniciar número de elementos

        // Reinserir cada elemento com novo cálculo de hash
        for (ClientePNode node : tempNodes) {
            if (node != null) {
                adicionarNovoCliente(node);
            }
        }

        ShowMessage.displayMessage("Rehash concluído com sucesso!", "INFO", false);

    } catch (Exception e) {
        ShowMessage.displayMessage("Erro ao realizar o rehash: " + e.getMessage(), "ERROR", true);
    }
}

// Função auxiliar para obter o próximo número primo
private int getNextPrime(int n) {
    while (!isPrime(n)) {
        n++;
    }
    return n;
}

// Função auxiliar para verificar se um número é primo
private boolean isPrime(int n) {
    if (n < 2) return false;
    for (int i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) return false;
    }
    return true;
}


@Override
public int calcularHashCodeReHashing(String key) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calcularHashCodeReHashing'");
}
 
}
