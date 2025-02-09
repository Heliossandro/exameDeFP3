package src.pages.produto;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.components.utils.AbstractHashTableCoalashed;
import src.components.utils.SaveWriteReadInteface;
import src.components.utils.ShowMessage;

import java.util.*;


public class ProdutosDadosTable extends AbstractHashTableCoalashed
{
   public ProdutosDadosTable(String fileName, int tableSize)
   {
		super(fileName, tableSize);	
   }	
   public ProdutosDadosTable( )
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
	ProdutosPNode node = (ProdutosPNode)getEmptyNode();

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
public ProdutosPNode getNode(String key) throws NullPointerException
{		
	//calcula a posicao de entrada na tabela apartir da chave key
	int tablePosition = calcularHashCode( key );

	ProdutosPNode tmp = (ProdutosPNode)getNode( tablePosition );

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
	ProdutosDadosTable table = new ProdutosDadosTable("produtos.dat", 100);
	
	ProdutosPNode tmp = table.getNode(key);
	
	return !tmp.isEmptyNode();
}


 
public static Vector getAllNodes()
{
	Vector listaNodes = new Vector();
	
	ProdutosDadosTable hashProduto = new ProdutosDadosTable("produtos.dat", 100);
	
	ProdutosPNode tmp = new ProdutosPNode();
		
	hashProduto.openFile();
	
	try
	{
		hashProduto.stream.seek(8);
		
		for(int i=0; i < hashProduto.tableSize; i++)
		{
			tmp.read( hashProduto.stream );
			
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
public void adicionarNovoProduto(ProdutosPNode node)
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
public void sobrePorRegisto(ProdutosPNode node, int posTabela)
{	
	ProdutosPNode no = (ProdutosPNode)getNode(posTabela);	
	
	node.setNext(no.getNext());
	
	node.setPrev(no.getPrev());	
	
	adicionarNoFicheiro(node, posTabela);
	
}
//segredo do projecto
//adiciona na lista de colisoes
public void adicionarNaListaColisoes(ProdutosPNode node, int lastColision)
{		
	ProdutosPNode no = (ProdutosPNode)getNode(lastColision);
	
	 //se nao houver ninguem na lista, adicionar
	if (no.getNext() == null && lastColision != tableSize - 1)
	{			
		no.setNext(node);
		node.setPrev(no);
	
		adicionarNoFicheiro(node, tableSize - 1);				
	}
	else
	{
		ProdutosPNode tmp = no.getNext();
	
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
	return (2 * 4 + tablePosition * ProdutosPNode.sizeof());
 }


public SaveWriteReadInteface getEmptyNode()
{
	return new ProdutosPNode();
}

public static int getNextID()
{
	//ProdutosDadosTable hashProduto = new ProdutosDadosTable("produtos.dat",100);
	//return hashProduto.getNextAutoId() + 1;
	
	return 1;
}
/*Listar os dados dos alunos numa comboBox*/
public static void listarProdutos()
{
	ProdutosDadosTable hashProduto = new ProdutosDadosTable("produtos.dat",100);
	ProdutosPNode tmp = (ProdutosPNode)hashProduto.getEmptyNode();
	String output = "\n";
	
	try
	{
			hashProduto.openFile();
			
			hashProduto.stream.seek(8);
			
			for(int i = 0; i < hashProduto.tableSize;++i)
			{
				tmp.read(hashProduto.stream);
				
				if(!tmp.getKey().equals(""))
				{						
					   output += tmp.toString();
					
					output += "------------------------------\n";
				}						
			}
			
			JTextArea area = new JTextArea(40, 50);
			area.setText(output);
			area.setEditable(false);
			
			JOptionPane.showMessageDialog(null, new JScrollPane(area), "Listagem de Produtos", JOptionPane.INFORMATION_MESSAGE);
			
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
