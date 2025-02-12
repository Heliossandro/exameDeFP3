package src.pages.apresentacao;

import javax.swing.*;
import src.pages.login.LoginInterface;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ApresentacaoInterface extends JFrame {
    private PainelCentro centro;
    private PainelSul sul;

    public ApresentacaoInterface() {
        setTitle("Tela de Boas-Vindas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220));

        String caminhoImg = "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\programação 2\\openJava\\HeliossandroAfonso33048\\imagens\\banerApresentacao.png";
        JLabel lblImage = new JLabel();
        try {
            BufferedImage originalImage = ImageIO.read(new File(caminhoImg));
            int newHeight = 250;
            int newWidth = (newHeight * originalImage.getWidth()) / originalImage.getHeight();
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(resizedImage));
        } catch (IOException e) {
            lblImage.setText("Imagem não encontrada");
        }
        JPanel painelNorte = new JPanel(new FlowLayout());
        painelNorte.setBackground(new Color(180, 180, 180));
        painelNorte.add(lblImage);
        add(painelNorte, BorderLayout.NORTH);

        centro = new PainelCentro();
        add(centro, BorderLayout.CENTER);

        sul = new PainelSul();
        add(sul, BorderLayout.SOUTH);

        setVisible(true);
    }

    public class PainelCentro extends JPanel {
        JTextArea txtAInfPrincipal;
        JCheckBox jcbCheck;

        public PainelCentro() {
            setLayout(new BorderLayout());
            setBackground(new Color(245, 245, 245));
            txtAInfPrincipal = new JTextArea();
            txtAInfPrincipal.setText("Bem-vindo à PerfumariaHS!\n\n" +
                    "Somos uma agência especializada na venda de perfumes e cremes.\n" +
                    "Este projeto foi criado para a disciplina de Fundamentos de Programação II, \n" +
                    "no curso de Engenharia Informática da UCAN.\n\n" +
                    "Criador: Heliossandro Afonso | ID: 33048 | Tema: Gestão de uma Perfumaria\n\n" +
                    "Se concorda com os termos e condições, clique em Concordar.");
            txtAInfPrincipal.setWrapStyleWord(true);
            txtAInfPrincipal.setLineWrap(true);
            txtAInfPrincipal.setEditable(false);
            txtAInfPrincipal.setFont(new Font("Arial", Font.PLAIN, 14));
            txtAInfPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(new JScrollPane(txtAInfPrincipal), BorderLayout.CENTER);

            jcbCheck = new JCheckBox("Concordo com os termos de uso");
            jcbCheck.setFont(new Font("Arial", Font.BOLD, 12));
            jcbCheck.setBackground(new Color(245, 245, 245));
            jcbCheck.setForeground(Color.DARK_GRAY);
            jcbCheck.addActionListener(e -> sul.ativarBtn(jcbCheck.isSelected()));
            add(jcbCheck, BorderLayout.SOUTH);
        }
    }

    public class PainelSul extends JPanel {
        private JButton btnEntrar, btnSair;

        public PainelSul() {
            setLayout(new FlowLayout());
            setBackground(new Color(180, 180, 180));
            
            btnEntrar = criarBotao("Entrar", new Color(100, 100, 100));
            btnEntrar.addActionListener(e -> {
                new LoginInterface();
                dispose();
            });
            add(btnEntrar);

            btnSair = criarBotao("Sair", new Color(150, 150, 150));
            btnSair.addActionListener(e -> dispose());
            add(btnSair);
            
            ativarBtn(false);
        }

        private JButton criarBotao(String texto, Color cor) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setForeground(Color.WHITE);
            botao.setBackground(cor);
            botao.setFocusPainted(false);
            botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            return botao;
        }

        public void ativarBtn(boolean status) {
            btnEntrar.setEnabled(status);
        }
    }

    public static void main(String[] args) {
        new ApresentacaoInterface();
    }
}
