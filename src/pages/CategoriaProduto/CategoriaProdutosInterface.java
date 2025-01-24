package src.pages.CategoriaProduto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CategoriaProdutosInterface extends JFrame {

    private PainelSul sul;
    private PainelCentro centro;
    private static int contadorId = 1; // Contador para geração de IDs infinitos

    public CategoriaProdutosInterface() {
        super("Categoria Dos Produtos");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField txtId;
        private JTextField txtTipoDoDocumento;

        public PainelCentro() {
            setLayout(new GridBagLayout()); // Usa GridBagLayout para maior controle
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem interna
            setBackground(new Color(245, 245, 245)); // Cor de fundo

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Campo de ID (gerado automaticamente)
            JLabel lblId = new JLabel("ID:");
            lblId.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblId, gbc);

            txtId = new JTextField(String.valueOf(contadorId++));
            txtId.setEditable(false); // Torna o campo de ID somente leitura
            txtId.setFont(new Font("Arial", Font.BOLD, 14));
            txtId.setHorizontalAlignment(JTextField.CENTER);
            txtId.setPreferredSize(new Dimension(200, 30)); // Define largura e altura
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(txtId, gbc);

            // Campo para Categoria
            JLabel lblCategoria = new JLabel("Categoria:");
            lblCategoria.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(lblCategoria, gbc);

            txtTipoDoDocumento = new JTextField();
            txtTipoDoDocumento.setFont(new Font("Arial", Font.PLAIN, 14));
            txtTipoDoDocumento.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            txtTipoDoDocumento.setPreferredSize(new Dimension(200, 30)); // Define largura e altura
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(txtTipoDoDocumento, gbc);
        }

        public String getCategoria() {
            return txtTipoDoDocumento.getText();
        }

        public int getId() {
            return Integer.parseInt(txtId.getText());
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alinhamento e espaçamento
            setBackground(new Color(245, 245, 245)); // Cor de fundo

            // Botão Salvar
            btnSalvar = new JButton("Salvar");
            estilizarBotao(btnSalvar);
            add(btnSalvar);

            // Botão Cancelar
            btnCancelar = new JButton("Cancelar");
            estilizarBotao(btnCancelar);
            add(btnCancelar);

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        private void estilizarBotao(JButton botao) {
            botao.setFocusPainted(false);
            botao.setBackground(new Color(0, 120, 215)); // Azul
            botao.setForeground(Color.WHITE);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Efeito hover
            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(new Color(0, 100, 180));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(new Color(0, 120, 215));
                }
            });
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar) {
                String categoria = centro.getCategoria();
                int id = centro.getId();
                JOptionPane.showMessageDialog(null, "ID: " + id + "\nCategoria: " + categoria, "Dados Salvos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new CategoriaProdutosInterface();
    }
}
