package src.pages.menuPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import src.pages.CategoriaProduto.*;
import src.pages.produto.*;
import src.pages.cliente.*;
import src.pages.fornecedor.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuItem adicionarCliente, adicionarProduto, adicionarCategoria, pesquisarClientesPorNome, listarClientes,
                        listarProduto, pesquisarProduto, adicionarEstoque, listarEstoque, adicionarFornecedor, listarFornecedor, pesquisarFornecedorPeloNome;

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
        
        adicionarProduto = new JMenuItem("Adicionar Produto");
        adicionarProduto.addActionListener(this); 
        menuProduto.add(adicionarProduto); 
        
        menuProduto.add(listarProduto = new JMenuItem("Listar Produtos"));
        listarProduto.addActionListener(this);

        JMenu menuCliente = new JMenu("Cliente");
        menuCliente.setForeground(Color.WHITE);

        // Adicionar Cliente
        adicionarCliente = new JMenuItem("Cadastrar Cliente");
        adicionarCliente.addActionListener(this);
        menuCliente.add(adicionarCliente);
          
        // pesquisar cliente por nome
        pesquisarClientesPorNome = new JMenuItem("Pesquisar clientes por nome");
        pesquisarClientesPorNome.addActionListener(this);
        menuCliente.add(pesquisarClientesPorNome);

        listarClientes = new JMenuItem("Listar Clientes");
        listarClientes.addActionListener(this);
        menuCliente.add(listarClientes);

        JMenu menuVenda = new JMenu("Venda");
        menuVenda.setForeground(Color.WHITE);
        menuVenda.add(new JMenuItem("Registrar Venda"));
        menuVenda.add(new JMenuItem("Listar Vendas"));

        JMenu menuSobre = new JMenu("Sobre");
        menuSobre.setForeground(Color.WHITE);
        menuSobre.add(new JMenuItem("Sobre o Sistema"));
        menuSobre.add(new JMenuItem("Ajuda"));

        adicionarCategoria = new JMenuItem("Adicionar Categorias de produtos");
        adicionarCategoria.addActionListener(this);  
        menuSobre.add(adicionarCategoria); 

        JMenu menuFornecedor = new JMenu("Fornecedor");

        menuFornecedor.add(adicionarFornecedor = new JMenuItem("Cadastrar novo fornecedor"));
        adicionarFornecedor.addActionListener(this);

        menuFornecedor.add(listarFornecedor = new JMenuItem("Listar fornecedor"));
        listarFornecedor.addActionListener(this);

        menuFornecedor.add(pesquisarFornecedorPeloNome = new JMenuItem("Pesquisar fornecedor"));
        adicionarFornecedor.addActionListener(this);

        menuBar.add(menuProduto);
        menuBar.add(menuCliente);
        menuBar.add(menuVenda);
        menuBar.add(menuFornecedor);
        menuBar.add(menuSobre);

        adicionarProduto.addActionListener(this);

        return menuBar;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == adicionarProduto) {
            ProdutoVisao produtoVisao = new ProdutoVisao();
            produtoVisao.setVisible(true);  
        }

        if (evt.getSource() == adicionarFornecedor) {
            new FornecedorVisao();
        }

        if (evt.getSource() == listarFornecedor) {
            FornecedoresDadosTable.listarFornecedores();
        }

        if (evt.getSource() == pesquisarFornecedorPeloNome) {
            PesquisarPorNomeFornecedor.abrirJanela(this);;
        }

        if(evt.getSource() == adicionarCategoria){
            CategoriaProdutosInterface categoriaProdutosInterface = new CategoriaProdutosInterface();
            categoriaProdutosInterface.setVisible(true);
        }

        if (evt.getSource() == pesquisarClientesPorNome) {
            PesquisarPorNomeCliente.abrirJanela(this);
        }
        
        if(evt.getSource() == adicionarCliente){
            ClienteVisao clienteVisao = new ClienteVisao();
            clienteVisao.setVisible(true);
        }

        if(evt.getSource() == listarClientes){
            ClientesDadosTable.listarClientes();
        }

        if(evt.getSource() == listarProduto){
            ProdutosDadosTable.listarProdutos();
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