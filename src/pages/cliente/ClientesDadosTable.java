package src.pages.cliente;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.components.utils.AbstractHashTableCoalashed;
import src.components.utils.SaveWriteReadInteface;
import src.components.utils.ShowMessage;

import java.util.*;


public class ClientesDadosTable extends AbstractHashTableCoalashed
{
   public ClientesDadosTable(String fileName, int tableSize)
   {
		super(fileName, tableSize);	
   }	
   public ClientesDadosTable( )
   {

   }

   public int calcularHashCodeReHashing(String key)
   {
		return 0;
   }

   //calcula a posicao de entrada na tabela apartir da chave key
   // devolve o hush code de uma determinada tabela
	
   public int calcularHashCode(String key)
   {
	return  Math.abs(key.hashCode()) % tableSize;
   }
   //devolve o no q esta na posicao <tablePosition> da tabela
   public SaveWriteReadInteface getNode(int tablePosition)
   {
	ClientesPNode node = (ClientesPNode)getEmptyNode();

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
 
 //devolve o  no cuja chave e <key>
//podemos usar este metodo para pesquisar por nome (key)
public ClientesPNode getNode(String key) throws NullPointerException
{		
	//calcula a posicao de entrada na tabela apartir da chave key
	int tablePosition = calcularHashCode( key );

	ClientesPNode tmp = (ClientesPNode)getNode( tablePosition );

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

//devolve todos nos da tabela <nao vazios>

public static boolean exists(String key)
{
	ClientesDadosTable table = new ClientesDadosTable("clientes.DAT", 100);
	
	ClientesPNode tmp = table.getNode(key);
	
	return !tmp.isEmptyNode();
}


 
public static Vector getAllNodes()
{
	Vector listaNodes = new Vector();
	
	ClientesDadosTable hashCliente = new ClientesDadosTable("clientes.DAT", 100);
	
	ClientesPNode tmp = new ClientesPNode();
		
	hashCliente.openFile();
	
	try
	{
		hashCliente.stream.seek(8);
		
		for(int i=0; i < hashCliente.tableSize; i++)
		{
			tmp.read( hashCliente.stream );
			
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
public void adicionarNovoCliente(ClientesPNode node)
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
public void sobrePorRegisto(ClientesPNode node, int posTabela)
{	
	ClientesPNode no = (ClientesPNode)getNode(posTabela);	
	
	node.setNext(no.getNext());
	
	node.setPrev(no.getPrev());	
	
	adicionarNoFicheiro(node, posTabela);
	
}
//segredo do projecto
//adiciona na lista de colisoes
public void adicionarNaListaColisoes(ClientesPNode node, int lastColision)
{		
	ClientesPNode no = (ClientesPNode)getNode(lastColision);
	
	 //se nao houver ninguem na lista, adicionar
	if (no.getNext() == null && lastColision != tableSize - 1)
	{			
		no.setNext(node);
		node.setPrev(no);
	
		adicionarNoFicheiro(node, tableSize - 1);				
	}
	else
	{
		ClientesPNode tmp = no.getNext();
	
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
	return (2 * 4 + tablePosition * ClientesPNode.sizeof());
 }


public SaveWriteReadInteface getEmptyNode()
{
	return new ClientesPNode();
}

public static int getNextID()
{
	//ClientesDadosTable hashCliente = new ClientesDadosTable("clientes.DAT",100);
	//return hashCliente.getNextAutoId() + 1;
	
	return 1;
}
//
public static void pesquisarClientesPorGenero(String generoProcurado)
{
	ClientesDadosTable hashCliente = new ClientesDadosTable("clientes.DAT",100);
	ClientesPNode tmp = (ClientesPNode)hashCliente.getEmptyNode();
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
	ClientesDadosTable hashCliente = new ClientesDadosTable("clientes.DAT",100);
	ClientesPNode tmp = (ClientesPNode)hashCliente.getEmptyNode();
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


//888888888888888888888888888888
public void reHashFile(int nTableSize)
{
	//implementar o reHash
}


}
