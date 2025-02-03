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

    public SaveWriteReadInteface getNode(int tablePosition)
   {
    ClientePNode node = (ClientePNode)getEmptyNode();

    long pos = getFilePosition( tablePosition );

    try
    {	
        stream.seek( pos );

        node.read( stream );
    
        //se nome nao vazio
        if ( node.isEmptyNode() == false )
        {
            return node;
        }
    }		
    catch (Exception ex)
    {
        ShowMessage.displayMessage("[GetNode] Falha no posicionamento do ficheiro " + fileName,
        "ERROR", true);	
    }

    return null;
 }

 public ClientePNode getNode(String key) throws NullPointerException
{		
    //calcula a posicao de entrada na tabela apartir da chave key
    int tablePosition = calcularHashCode( key );

    ClientePNode tmp = (ClientePNode)getNode( tablePosition );

        if (tmp != null)
        {
            while ( !tmp.getKey().equalsIgnoreCase(""))
            {
                    if ( tmp.getKey().equalsIgnoreCase( key ) )
                            return tmp;

                    tmp = tmp.getNext();
            }			
        }		
    return tmp;
}

public static boolean exists(String key)
{
    ClienteDadosTable table = new ClienteDadosTable("clientes.DAT", 100);
    
    ClientePNode tmp = table.getNode(key);
    
    return !tmp.isEmptyNode();
}


 
public static Vector getAllNodes()
{
    Vector listaNodes = new Vector();
    
    ClienteDadosTable hashCadaver = new ClienteDadosTable("clientes.DAT", 100);
    
    ClientePNode tmp = new ClientePNode();
        
    hashCadaver.openFile();
    
    try
    {
        hashCadaver.stream.seek(8);
        
        for(int i=0; i < hashCadaver.tableSize; i++)
        {
            tmp.read( hashCadaver.stream );
            
            if( !tmp.isEmptyNode()  )
            {
                listaNodes.addElement(tmp.getKey());
                System.out.println( tmp.getModel().getNome() );
            }
            else
                System.out.println(i);
        }
    }
    catch(Exception ex)
    {
        System.out.println("corpodadostables.java");
    }
    
    return listaNodes;
}




//adiciona na tabela e depois no ficheiro
public void adicionarNovoCadaver(ClientePNode node)
{
    try
    {
        stream.seek(0);
        
        int nTableSize = stream.readInt();
        int nElements = stream.readInt();					

        
        // Trata do ouverFlow da tabela a 80%
        if ( (nElements + 1) / nTableSize >= 0.8 )
            ouverFlowFile( nTableSize, nElements );
    }
    catch (Exception ex)
    {
        ShowMessage.displayMessage("Falha ao adicionar um aluno " + fileName, "ERROR", true);	
    }

     //calcula a posicao na tabela
     int posTabela = calcularHashCode( node.getKey() );

     //se nao tiver nenhum elemento nesta posicao adiciona
     if ( getNode( posTabela ) == null)
              
         adicionarNoFicheiro(node, posTabela); 
    
     //se tiver alguem e ele quiser sobrepor 
     else if (getNode(posTabela).getKey().equalsIgnoreCase(node.getKey()))
     {
        if (JOptionPane.showConfirmDialog(null, "Este Registo ja existe Quer Sobrepor") == JOptionPane.YES_OPTION)
            
            sobrePorRegisto(node, posTabela);
        else
            return;			 
     }		 
     else
         //se houver colisao, colocar na lista de colisoes
         adicionarNaListaColisoes(node, posTabela);
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
public void adicionarNaListaColisoes(ClientePNode node, int lastColision)
{		
    ClientePNode no = (ClientePNode)getNode(lastColision);
    
     //se nao houver ninguem na lista, adicionar
    if (no.getNext() == null && lastColision != tableSize - 1)
    {			
        no.setNext(node);
        node.setPrev(no);
    
        adicionarNoFicheiro(node, tableSize - 1);				
    }
    else
    {
        ClientePNode tmp = no.getNext();
    
        //procura 1 espaco vazio apartir do fim
        for (int i = tableSize - 1; i >= 0; --i)
        {
            if (tmp.getNext() == null) //se houver lugar vago adicionar
            {
                tmp.setNext(node);
                node.setPrev(tmp);
                //adiciona no fim da tabela se houver espaco
                adicionarNoFicheiro(node, i);
                return;
            }
            else
                tmp = tmp.getNext();				
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
    //ClienteDadosTable hashcliente = new ClienteDadosTable("clientes.DAT",100);
    //return hashCadaver.getNextAutoId() + 1;
    
    return 1;
}
//
public static void pesquisarCadaversPorGenero(String generoProcurado)
{
    ClienteDadosTable hashCadaver = new ClienteDadosTable("clientes.DAT",100);
    ClientePNode tmp = (ClientePNode)hashCadaver.getEmptyNode();
    String output = "\n";
    
    try
    {
            hashCadaver.openFile();
            
            hashCadaver.stream.seek(8);
            
            for(int i = 0; i < hashCadaver.tableSize;++i)
            {
                tmp.read(hashCadaver.stream);
                
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
public static void listarCadavers()
{
    ClienteDadosTable hashCadaver = new ClienteDadosTable("clientes.DAT",100);
    ClientePNode tmp = (ClientePNode)hashCadaver.getEmptyNode();
    String output = "\n";
    
    try
    {
            hashCadaver.openFile();
            
            hashCadaver.stream.seek(8);
            
            for(int i = 0; i < hashCadaver.tableSize;++i)
            {
                tmp.read(hashCadaver.stream);
                
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


//888888888888888888888888888888
public void reHashFile(int nTableSize)
{
    //implementar o reHash
}

@Override
public int calcularHashCodeReHashing(String key) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calcularHashCodeReHashing'");
}
 
}
