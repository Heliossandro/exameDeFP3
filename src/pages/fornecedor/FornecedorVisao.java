package src.pages.fornecedor;

/*----------------------------------------
Nome: Osvaldo Ramos, 2817
Tema: 
File: FornecedorVisao.java
------------------------------------------*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FornecedorVisao extends JFrame {    
    private PainelSul sul;
    private PainelCentro centro;

    public FornecedorVisao() {
        super("Registar Novo Fornecedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Fundo claro

        // Adiciona a imagem no topo, redimensionada
        JLabel imagemLabel = new JLabel(redimensionarImagem(
            "C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/clientes2.png", 80, 80));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(imagemLabel, BorderLayout.NORTH);

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 320);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, nomeJTF, numTelefoneJTF, enderecoJTF;

        public PainelCentro() {
            setLayout(new GridBagLayout());
            setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;

            add(new JLabel("ID"), gbc);
            gbc.gridx = 1;
            add(idJTF = criarCampoTexto(), gbc);
            idJTF.setText("" + FornecedoresDadosTable.getNextID());
            idJTF.setEditable(false);

            gbc.gridy++;
            gbc.gridx = 0;
            add(new JLabel("Nome"), gbc);
            gbc.gridx = 1;
            add(nomeJTF = criarCampoTexto(), gbc);

            gbc.gridy++;
            gbc.gridx = 0;
            add(new JLabel("Número de Telefone"), gbc);
            gbc.gridx = 1;
            add(numTelefoneJTF = criarCampoTexto(), gbc);

            gbc.gridy++;
            gbc.gridx = 0;
            add(new JLabel("Endereço"), gbc);
            gbc.gridx = 1;
            add(enderecoJTF = criarCampoTexto(), gbc);
        }

        private JTextField criarCampoTexto() {
            JTextField campo = new JTextField();
            campo.setPreferredSize(new Dimension(150, 25));
            campo.setBorder(new CompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true), 
                new EmptyBorder(5, 5, 5, 5)
            ));
            return campo;
        }

        // ---- Métodos GET ----
        public int getId() { return Integer.parseInt(idJTF.getText()); }
        public String getNome() { return nomeJTF.getText().trim(); }
        public String getNumTelefone() { return numTelefoneJTF.getText().trim(); }
        public String getEndereco() { return enderecoJTF.getText().trim(); }

        // Método para salvar os dados
        public void salvar() {    
            FornecedorModelo modelo = new FornecedorModelo(getId(), getNome(), getNumTelefone(), getEndereco());
            JOptionPane.showMessageDialog(null, "Fornecedor salvo:\n" + modelo.toString());
            modelo.salvar();
        }

        public void limparCampos() {
            idJTF.setText("" + FornecedoresDadosTable.getNextID());
            nomeJTF.setText("");
            numTelefoneJTF.setText("");
            enderecoJTF.setText("");
        }        
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton salvarJBT, cancelarJBT;

        public PainelSul() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            setBackground(Color.WHITE);

            salvarJBT = criarBotao("Salvar", new Color(46, 139, 87));
            cancelarJBT = criarBotao("Cancelar", new Color(220, 20, 60));

            add(salvarJBT);
            add(cancelarJBT);

            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        private JButton criarBotao(String texto, Color cor) {
            JButton botao = new JButton(texto);
            botao.setPreferredSize(new Dimension(120, 35));
            botao.setForeground(Color.WHITE);
            botao.setBackground(cor);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setFocusPainted(false);
            botao.setBorder(new LineBorder(cor.darker(), 1, true));

            // Efeito hover
            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor);
                }
            });

            return botao;
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == salvarJBT) {
                centro.salvar();
                centro.limparCampos();
            } else {
                dispose();
            }
        }
    }

    private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon imagemOriginal = new ImageIcon(caminho);
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }

    public static void main(String args[]) {
        new FornecedorVisao();
    }
}
