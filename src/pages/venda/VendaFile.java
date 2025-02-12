package src.pages.venda;



import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;
import java.util.*;

public class VendaFile extends ObjectsFile
{
	
	public VendaFile()
	{
		super("VENDA.DAT", new VendaModelo() );
	}
	
	public void salvarDados(VendaModelo modelo)
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