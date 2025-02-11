package src.pages.fornecedor;

import javax.swing.*;
import SwingComponents.*;
import java.io.*;

public class FornecedorFile extends ObjectsFile
{
	
	public FornecedorFile()
	{
		super("FORNECEDOR.DAT", new FornecedorModelo() );
	}
	
	public void salvarDados(FornecedorModelo modelo)
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
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Fornecedor");
		}
	}

}