package src.pages.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteInterface extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;

    public ClienteInterface(){
        super("Cadastrar Clientes");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add( sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel{
        private JTextField txtNome, txtNumTelefone, txtEmail;
        private JComboBox cbDocumentos;
        private char genero;

        public PainelCentro(){
            setLayout( new GridLayout(8,2));

            add(new JLabel("Nome"));
            add(txtNome = new JTextField());

            add(new JLabel("Numero de telefone"));
            add(txtNumTelefone = new JTextField());

/*             add(new JLabel("Email"));
            add(genero = new JTextField());

            add(new JLabel("Nome"));
            add(txtNome = new JTextField()); */


        }
    }

    class PainelSul extends JPanel implements ActionListener{
        private JButton btnSalvar, btnCancelar;
        
        public PainelSul(){
            setLayout(new FlowLayout());

            add( btnSalvar = new JButton("Salvar"));
            add( btnCancelar = new JButton("Cancelar"));

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt){
            if(evt.getSource() == btnSalvar){
                JOptionPane.showMessageDialog(null, "Salvar");
            }else{
                dispose();
            }
        }
    }

    public static void main (String args[]){
        new ClienteInterface();
    }
}
