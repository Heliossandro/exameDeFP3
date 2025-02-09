package src.pages.produto;



import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class ProdutoFile extends ObjectsFile
{
	
	public ProdutoFile()
	{
		super("PRODUTO.DAT", new ProdutoModelo() );
	}
	
	public void salvarDados(ProdutoModelo modelo)
	{
		try
		{
			//colocar o File Pointer no final do ficheiro
			stream.seek( stream.length() );
			
			//escrever os dados no ficheiro
			modelo.write(stream);

			incrementarProximoCodigo();

			JOptionPane.showMessageDialog(null, "Dados Salvos com Sucesso!");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Cliente");
		}
	}

}