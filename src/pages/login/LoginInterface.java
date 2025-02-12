package src.pages.login;

import javax.swing.*;
import javax.swing.border.*;
import src.pages.menuPrincipal.MenuPrincipal;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginInterface extends JFrame {
    private static final String EMAIL_FIXO = "12345";
    private static final String SENHA_FIXA = "12345";

    public LoginInterface() {
        setTitle("Login");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelLogin = new JPanel(new BorderLayout());
        painelLogin.setBackground(new Color(245, 245, 245)); // Cor mais neutra

        // Painel com a imagem à esquerda
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBackground(new Color(245, 245, 245));
        String caminhoImgLogin = "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\perfumaria.jpg";
        JLabel lblImageLogin = new JLabel();
        try {
            Image originalImageLogin = ImageIO.read(new File(caminhoImgLogin));
            Image resizedImageLogin = originalImageLogin.getScaledInstance(400, 650, Image.SCALE_SMOOTH); // Altura aumentada
            ImageIcon imageIconLogin = new ImageIcon(resizedImageLogin);
            lblImageLogin.setIcon(imageIconLogin);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar imagem de login.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        painelEsquerdo.add(lblImageLogin);
        painelLogin.add(painelEsquerdo, BorderLayout.WEST);

        // Painel com campos de email e senha à direita
        JPanel painelDireito = new JPanel(new GridBagLayout());
        painelDireito.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Campos de entrada
        InputField emailField = new InputField("Email:");
        emailField.setPreferredSize(new Dimension(300, 30));
        painelDireito.add(emailField, gbc);

        gbc.gridy = 1;
        InputField senhaField = new InputField("Senha:");
        senhaField.setPreferredSize(new Dimension(300, 30));
        senhaField.setAsPasswordField();
        painelDireito.add(senhaField, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Adiciona painel de botões
        ButtonPanel buttonPanel = new ButtonPanel(emailField, senhaField);
        painelDireito.add(buttonPanel, gbc);

        painelLogin.add(painelDireito, BorderLayout.CENTER);

        add(painelLogin);
        setVisible(true);

        // Enter no campo de email leva para o campo de senha
        emailField.textField.addActionListener(e -> senhaField.textField.requestFocusInWindow());
    }

    // Classe para campos de entrada
    private class InputField extends JPanel {
        private JLabel lbl;
        private JTextField textField;

        public InputField(String label) {
            setLayout(new BorderLayout());
            lbl = new JLabel(label);
            lbl.setFont(new Font("Arial", Font.PLAIN, 18));
            textField = new JTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 18));
            add(lbl, BorderLayout.WEST);
            add(textField, BorderLayout.CENTER);
        }

        public String getText() {
            return textField.getText();
        }

        public void setAsPasswordField() {
            textField = new JPasswordField();
            textField.setFont(new Font("Arial", Font.PLAIN, 18));
            removeAll();
            add(lbl, BorderLayout.WEST);
            add(textField, BorderLayout.CENTER);
            revalidate();
            repaint();
        }

        public char[] getPassword() {
            return textField instanceof JPasswordField ? ((JPasswordField) textField).getPassword() : new char[0];
        }
    }

    // Classe para botões com estilo arredondado
    private class ButtonPanel extends JPanel {
        public ButtonPanel(InputField emailField, InputField senhaField) {
            setBackground(new Color(245, 245, 245));

            JButton btnEntrarLogin = criarBotaoPersonalizado("Entrar", new Color(169, 169, 169), Color.WHITE);
            JButton btnSairLogin = criarBotaoPersonalizado("Sair", new Color(169, 169, 169), Color.WHITE);
            

            btnEntrarLogin.addActionListener(evt -> {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                if (EMAIL_FIXO.equals(email) && SENHA_FIXA.equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    new MenuPrincipal().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            });

            senhaField.textField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        btnEntrarLogin.doClick();
                    }
                }
            });

            btnSairLogin.addActionListener(evt -> dispose());

            add(btnEntrarLogin);
            add(Box.createRigidArea(new Dimension(10, 0)));
            add(btnSairLogin);
        }

        private JButton criarBotaoPersonalizado(String texto, Color corFundo, Color corTexto) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 18));
            botao.setForeground(corTexto);
            botao.setBackground(corFundo);
            botao.setFocusPainted(false);
            botao.setBorder(new CompoundBorder(
                new LineBorder(corFundo.darker(), 2, true), // Borda arredondada
                new EmptyBorder(10, 20, 10, 20) // Espaçamento interno
            ));
            botao.setOpaque(true);
            botao.setBorderPainted(false);

            // Efeito ao passar o mouse
            botao.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    botao.setBackground(new Color(34, 139, 34)); // Verde ao passar o mouse
                }

                public void mouseExited(MouseEvent e) {
                    botao.setBackground(corFundo);
                }
            });

            return botao;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterface::new);
    }
}
