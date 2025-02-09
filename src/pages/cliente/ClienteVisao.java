package src.pages.cliente;

/*----------------------------------------
Nome: Osvaldo Ramos, 2817
Tema: 
File: ClienteVisao.java

------------------------------------------*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;

public class ClienteVisao extends JFrame
{	
	private PainelSul sul;
	private PainelCentro centro;
	
	public ClienteVisao()
	{
		super("Registar Novo Cliente");
	
		
		getContentPane().add( centro = new PainelCentro(), BorderLayout.CENTER);
		getContentPane().add( sul = new PainelSul(), BorderLayout.SOUTH);
		
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class PainelCentro extends JPanel
	{
		private JTextField idJTF, nomeJTF, numTelefoneJTF, emailJTF;
		private JComboBox generoJCB;
		private String generos[] = {"Masculino", "Feminino"};
		public PainelCentro()
		{
			
			setLayout( new GridLayout(8, 2) );
			
			//linha 1
			add( new JLabel("ID") );
			add( idJTF = new JTextField() );
			ClienteFile file = new ClienteFile();
			idJTF.setText("" + file.getProximoCodigo()); //aqui ele vai gerar id automatico atraves daquele Ficheiro antigo de FP2 e vai colocar
			idJTF.setFocusable(false);
			
			//linha 2
			add( new JLabel("Nome") );
			add( nomeJTF = new JTextField() );

			//linha 3
			add( new JLabel("Numero de Telefone") );
			add( numTelefoneJTF = new JTextField() );

						//linha 3
			add( new JLabel("email") );
			add( emailJTF = new JTextField() );

			add( new JLabel("Genero") );
			add( generoJCB = new JComboBox(generos) );
			
			
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

		public  String getEmail(){
			return emailJTF.getText().trim();
		}
		
		public String getGenero()
		{
			return String.valueOf( generoJCB.getSelectedItem() );
		}

		 public boolean isEmpty(Object valor)
    {
        return String.valueOf(valor).equals("") || valor == null || String.valueOf(valor).equals("0") || String.valueOf(valor).equals("0.0");
    }


     public boolean verificarCampos()
        {
            if(isEmpty(getId()) || isEmpty(getNome()) || isEmpty(getNumTelefone()) || isEmpty(getEmail()) || isEmpty(getGenero()))
                    return false;
                return true; 
        }

		//metodo salvar = ligacao entre a visao e o modelo
		public void salvar()
		{	
			//enviamos os dados da visao para o modelo
			ClienteModelo modelo = new ClienteModelo(getId(), getNome(),
			getNumTelefone(), getEmail(), getGenero());
				
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
			{
				if(centro.verificarCampos())
                    		{
					centro.salvar();
				}
				else
					JOptionPane.showMessageDialog(null, "Campo vazios", "Verificador de campos", JOptionPane.ERROR_MESSAGE);

			}
			else
				dispose();
		}
	}
	
	public static void main(String args[])
	{
		new ClienteVisao();
	}
}