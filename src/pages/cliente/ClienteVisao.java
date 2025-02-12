package src.pages.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteVisao extends JFrame {	
    private PainelSul sul;
    private PainelCentro centro;

    public ClienteVisao() {
        super("Registar Novo Cliente");

        getContentPane().setBackground(new Color(240, 240, 240)); // Fundo claro

        // Adiciona a imagem no topo, redimensionada
        JLabel imagemLabel = new JLabel(redimensionarImagem("C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/clientes.png", 100, 100));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(imagemLabel, BorderLayout.NORTH);

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, nomeJTF, numTelefoneJTF, emailJTF;

        public PainelCentro() {
            setLayout(new GridLayout(4, 2, 10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            add(new JLabel("ID"));
            add(idJTF = criarTextField());
            idJTF.setText("" + ClientesDadosTable.getNextID());
            idJTF.setFocusable(false);

            add(new JLabel("Nome"));
            add(nomeJTF = criarTextField());

            add(new JLabel("Número de Telefone"));
            add(numTelefoneJTF = criarTextField());

            add(new JLabel("Email"));
            add(emailJTF = criarTextField());
        }

        private JTextField criarTextField() {
            JTextField field = new JTextField();
            field.setFont(new Font("Arial", Font.PLAIN, 14));
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            return field;
        }

        public int getId() {
            return Integer.parseInt(idJTF.getText());
        }

        public String getNome() {
            return nomeJTF.getText().trim();
        }

        public String getNumTelefone() {
            return numTelefoneJTF.getText().trim();
        }

        public String getEmail() {
            return emailJTF.getText().trim();
        }

        public void salvar() {	
            ClienteModelo modelo = new ClienteModelo(getId(), getNome(), getNumTelefone(), getEmail());
            JOptionPane.showMessageDialog(null, "Cliente salvo:\n" + modelo.toString());
            modelo.salvar();
        }

        public void limparCampos() {
            idJTF.setText("" + ClientesDadosTable.getNextID());
            nomeJTF.setText("");
            numTelefoneJTF.setText("");
            emailJTF.setText("");
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton salvarJBT, cancelarJBT;

        public PainelSul() {
            setLayout(new FlowLayout());
            setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            salvarJBT = criarBotao("Salvar", new Color(0, 153, 76), "C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/certo.png");
            cancelarJBT = criarBotao("Cancelar", new Color(204, 0, 0), "C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/nao.png");

            add(salvarJBT);
            add(cancelarJBT);
        }

        private JButton criarBotao(String texto, Color cor, String iconeCaminho) {
            JButton botao = new JButton(texto, redimensionarImagem(iconeCaminho, 20, 20));
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setForeground(Color.WHITE);
            botao.setBackground(cor);
            botao.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            botao.setFocusPainted(false);
            botao.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto à direita do ícone

            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor.darker());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor);
                }
            });

            botao.addActionListener(this);
            return botao;
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == salvarJBT) {
                centro.salvar();
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
        SwingUtilities.invokeLater(() -> new ClienteVisao());
    }
}
