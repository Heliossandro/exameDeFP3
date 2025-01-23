package src.pages.CategoriaProduto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CategoriaProdutosInterface extends JFrame {

    private PainelSul sul;
    private PainelCentro centro;

    public CategoriaProdutosInterface(){

        super("Categoria Dos produtos");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add( sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel{
        private JTextField txtTipoDoDocumento;

        public PainelCentro(){
            setLayout( new GridLayout(8,2));

            add(new JLabel("Categoria"));
            add(txtTipoDoDocumento = new JTextField());
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
        new CategoriaProdutosInterface();
    }
}
