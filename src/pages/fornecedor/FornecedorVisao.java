package src.pages.fornecedor;

/*----------------------------------------
Nome: Osvaldo Ramos, 2817
Tema: 
File: FornecedorVisao.java

------------------------------------------*/


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FornecedorVisao extends JFrame
{	
	private PainelSul sul;
	private PainelCentro centro;
	
	public FornecedorVisao()
	{
		super("Registar Novo Fornecedor");
	
		getContentPane().add( centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add( sul = new PainelSul(), BorderLayout.SOUTH);
		
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel
	{
		private JTextField idJTF, nomeJTF, numTelefoneJTF, enderecoJTF;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(8, 2) );
			
			//linha 1
			add( new JLabel("ID") );
			add( idJTF = new JTextField() );
			idJTF.setText("" + FornecedoresDadosTable.getNextID());
			idJTF.setEditable(false);
			
			//linha 2
			add( new JLabel("Nome") );
			add( nomeJTF = new JTextField() );

			//linha 3
			add( new JLabel("Numero de Telefone") );
			add( numTelefoneJTF = new JTextField() );

						//linha 3
			add( new JLabel("endereco") );
			add( enderecoJTF = new JTextField() );
		}
		
		// ---- metodos get da visao
		public int getId()
		{
			return Integer.parseInt( idJTF.getText() );
		}
		public String getNome()
		{
			return nomeJTF.getText().trim();
		}

		public String getNumTelefone()
		{
			return numTelefoneJTF.getText().trim();
		}

		public  String getEndereco(){
			return enderecoJTF.getText().trim();
		}

		//metodo salvar = ligacao entre a visao e o modelo
		public void salvar()
		{	
			//enviamos os dados da visao para o modelo
			FornecedorModelo modelo = new FornecedorModelo(getId(), getNome(),
			getNumTelefone(), getEndereco());
				
			JOptionPane.showMessageDialog(null, modelo.toString());
			
			modelo.salvar();
		}
		
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		private JButton salvarJBT, cancelarJBT;
		
		public PainelSul()
		{
			setLayout( new FlowLayout() );
			
			add( salvarJBT = new JButton("Salvar"));
			add( cancelarJBT = new JButton("Cancelar"));
				
			salvarJBT.addActionListener( this );
			cancelarJBT.addActionListener( this );
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == salvarJBT)
				centro.salvar();
			else
				dispose();
		}
	}
	
	public static void main(String args[])
	{
		new FornecedorVisao();
	}
} 