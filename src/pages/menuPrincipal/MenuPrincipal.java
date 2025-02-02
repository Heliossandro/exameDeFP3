package src.pages.menuPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.pages.CategoriaProduto.CategoriaProdutosInterface;
import src.pages.documento.DocumentoInterface;
import src.pages.produto.ProdutoInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuItem adicionarProduto, adicionarCategoria, adicionarDocumento;

    public MenuPrincipal() {
        setTitle("Menu Principal - Gestão de Perfumaria");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarLayout();
    }

    private void configurarLayout() {
        
        setJMenuBar(criarMenuBar());

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 15, 15)); // 2 linhas, 2 colunas, espaçamento
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margens internas
        mainPanel.setBackground(new Color(245, 245, 245)); // Fundo do painel principal

        // Criar blocos com imagens e botões
        mainPanel.add(criarBloco("Perfumes para Homens",
            "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\src\\components\\images\\perfumesHomens.jpg"));
        mainPanel.add(criarBloco("Perfumes para Mulheres",
            "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\src\\components\\images\\perfumesMulheres.jpg"));
        mainPanel.add(criarBloco("Cremes",
            "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\src\\components\\images\\cremes.jpg"));
        mainPanel.add(criarBloco("Kits",
            "C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\src\\components\\images\\conjuntos.jpg"));

        add(mainPanel, BorderLayout.CENTER);
    }

    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 144, 255));
        menuBar.setBorder(new EmptyBorder(5, 5, 5, 5));

        JMenu menuProduto = new JMenu("Produto");
        menuProduto.setForeground(Color.WHITE);
        adicionarProduto = new JMenuItem("Adicionar Produto");  // Inicializando a variável
        adicionarProduto.addActionListener(this);  // Associando o ouvinte de ação
        menuProduto.add(adicionarProduto);  // Adicionando ao menuProduto
        menuProduto.add(new JMenuItem("Listar Produtos"));


        JMenu menuCliente = new JMenu("Cliente");
        menuCliente.setForeground(Color.WHITE);
        menuCliente.add(new JMenuItem("Cadastrar Cliente"));
        menuCliente.add(new JMenuItem("Listar Clientes"));

        JMenu menuVenda = new JMenu("Venda");
        menuVenda.setForeground(Color.WHITE);
        menuVenda.add(new JMenuItem("Registrar Venda"));
        menuVenda.add(new JMenuItem("Listar Vendas"));

        JMenu menuEstoque = new JMenu("Estoque");
        menuEstoque.setForeground(Color.WHITE);
        menuEstoque.add(new JMenuItem("Adicionar ao Estoque"));
        menuEstoque.add(new JMenuItem("Listar Estoque"));

        JMenu menuSobre = new JMenu("Sobre");
        menuSobre.setForeground(Color.WHITE);
        menuSobre.add(new JMenuItem("Sobre o Sistema"));
        menuSobre.add(new JMenuItem("Ajuda"));
        adicionarDocumento = new JMenuItem("Adicionar DOcumentos");
        adicionarDocumento.addActionListener(this);  
        menuSobre.add(adicionarDocumento); 
        adicionarCategoria = new JMenuItem("Adicionar Categorias de produtos");
        adicionarCategoria.addActionListener(this);  
        menuSobre.add(adicionarCategoria); 

        menuBar.add(menuProduto);
        menuBar.add(menuCliente);
        menuBar.add(menuVenda);
        menuBar.add(menuEstoque);
        menuBar.add(menuSobre);

        adicionarProduto.addActionListener(this);

        return menuBar;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == adicionarProduto) {
            ProdutoInterface produtoInterface = new ProdutoInterface();
            produtoInterface.setVisible(true);  // Tornar a interface visível
        }

        if( evt.getSource() == adicionarDocumento){
            DocumentoInterface documentoInterface = new DocumentoInterface();
            documentoInterface.setVisible(true);
        }

        if(evt.getSource() == adicionarCategoria){
            CategoriaProdutosInterface categoriaProdutosInterface = new CategoriaProdutosInterface();
            categoriaProdutosInterface.setVisible(true);
        }
    }
    

    private JPanel criarBloco(String titulo, String caminhoImagem) {
        // Painel para cada bloco
        JPanel blocoPanel = new JPanel();
        blocoPanel.setLayout(new BorderLayout());
        blocoPanel.setBackground(Color.WHITE);
        blocoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Adicionar imagem
        JLabel imagemLabel = new JLabel();
        ImageIcon icon = new ImageIcon(caminhoImagem);
        Image imagemRedimensionada = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Ajustar tamanho
        imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adicionar botão estilizado
        JButton botao = new JButton("Ver os " + titulo);
        botao.setFocusPainted(false);
        botao.setBackground(new Color(0, 120, 215));
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover no botão
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(0, 100, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(0, 120, 215));
            }
        });

        botao.addActionListener(e -> JOptionPane.showMessageDialog(this, "Você clicou em: " + titulo));

        // Adicionar título
        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tituloLabel.setForeground(Color.DARK_GRAY);
        tituloLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Montar o bloco
        blocoPanel.add(tituloLabel, BorderLayout.NORTH);
        blocoPanel.add(imagemLabel, BorderLayout.CENTER);
        blocoPanel.add(botao, BorderLayout.SOUTH);

        return blocoPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.setVisible(true);
        });
    }
}