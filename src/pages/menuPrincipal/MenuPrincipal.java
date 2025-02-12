package src.pages.menuPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import src.pages.CategoriaProduto.*;
import src.pages.produto.*;
import src.pages.venda.PesquisarPorNomeVenda;
import src.pages.venda.VendasDadosTable;
import src.pages.venda.VendasInterface;
import src.pages.cliente.*;
import src.pages.fornecedor.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuItem adicionarCliente, adicionarProduto, adicionarCategoria, pesquisarClientesPorNome, listarClientes,
            listarProduto, pesquisarProduto, adicionarFornecedor, listarFornecedor, pesquisarFornecedorPeloNome,
            adicionarVenda, pesquisarVenda, listarVenda, sairItem;

    private JButton btnVerProdutos, btnComprarProduto;

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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(30, 30, 40)); // Cor escura

        // Painel central (Bem-vindo + botões)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mensagem de boas-vindas acima dos botões
        JLabel welcomeLabel = new JLabel("Bem-vindo à Perfumaria!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(200, 200, 200));
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(welcomeLabel, gbc);

        // Botão Ver Produtos
        gbc.gridy++;
        btnVerProdutos = new JButton("Ver Produtos");
        btnVerProdutos.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerProdutos.setBackground(new Color(70, 130, 180));
        btnVerProdutos.setForeground(Color.WHITE);
        btnVerProdutos.addActionListener(this);
        centerPanel.add(btnVerProdutos, gbc);

        // Botão Comprar Produto
        gbc.gridy++;
        btnComprarProduto = new JButton("Comprar Produto");
        btnComprarProduto.setFont(new Font("Arial", Font.BOLD, 16));
        btnComprarProduto.setBackground(new Color(34, 139, 34));
        btnComprarProduto.setForeground(Color.WHITE);
        btnComprarProduto.addActionListener(this);
        centerPanel.add(btnComprarProduto, gbc);

        

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

        this.revalidate();
        this.repaint();
    }

    private JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuProduto = new JMenu("Produto");
        menuProduto.setIcon(redimensionarImagem("C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\perfume.png", 40, 40));

        JMenu menuCliente = new JMenu("Cliente");
        menuCliente.setIcon(redimensionarImagem("C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\clientes.png", 40, 40));

        JMenu menuFornecedor = new JMenu("Fornecedor");
        menuFornecedor.setIcon(redimensionarImagem("C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\clientes2.png", 40, 40));

        JMenu menuVenda = new JMenu("Venda");
        menuVenda.setIcon(redimensionarImagem("C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\venda.png", 40, 40));

        JMenu menuSobre = new JMenu("Sobre");
        menuSobre.setIcon(redimensionarImagem("C:\\Users\\Heliossandro Afonso\\Documents\\Aulas\\FPIII\\projeto_exame\\image\\help.png", 40, 40));

        adicionarProduto = new JMenuItem("Adicionar Produto");
        adicionarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        listarProduto = new JMenuItem("Listar Produtos");
        pesquisarProduto = new JMenuItem("Pesquisar Produto");
        sairItem = new JMenuItem("Sair");
        menuProduto.add(adicionarProduto);
        menuProduto.add(listarProduto);
        menuProduto.add(pesquisarProduto);
        menuProduto.add(sairItem);

        adicionarCliente = new JMenuItem("Cadastrar Cliente");
        adicionarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        listarClientes = new JMenuItem("Listar Clientes");
        pesquisarClientesPorNome = new JMenuItem("Pesquisar Cliente");
        sairItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
        sairItem.addActionListener(e -> System.exit(0));
        menuCliente.add(adicionarCliente);
        menuCliente.add(listarClientes);
        menuCliente.add(pesquisarClientesPorNome);

        adicionarFornecedor = new JMenuItem("Cadastrar Fornecedor");
        adicionarFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        listarFornecedor = new JMenuItem("Listar Fornecedores");
        pesquisarFornecedorPeloNome = new JMenuItem("Pesquisar Fornecedor");
        menuFornecedor.add(adicionarFornecedor);
        menuFornecedor.add(listarFornecedor);
        menuFornecedor.add(pesquisarFornecedorPeloNome);

        adicionarVenda = new JMenuItem("Adicionar Venda");
        listarVenda = new JMenuItem("Listar Vendas");
        pesquisarVenda = new JMenuItem("Pesquisar Venda");
        menuVenda.add(adicionarVenda);
        menuVenda.add(listarVenda);
        menuVenda.add(pesquisarVenda);

        menuSobre.add(adicionarCategoria = new JMenuItem("Adicionar categorias"));
        menuSobre.add(new JMenuItem("Sobre o Sistema"));
        menuSobre.add(new JMenuItem("Ajuda"));

        adicionarProduto.addActionListener(this);
        listarProduto.addActionListener(this);
        pesquisarProduto.addActionListener(this);
        adicionarCliente.addActionListener(this);
        pesquisarClientesPorNome.addActionListener(this);
        listarClientes.addActionListener(this);
        adicionarCategoria.addActionListener(this);
        adicionarFornecedor.addActionListener(this);
        listarFornecedor.addActionListener(this);
        adicionarFornecedor.addActionListener(this);
        adicionarVenda.addActionListener(this);
        listarVenda.addActionListener(this);
        pesquisarVenda.addActionListener(this);
        adicionarProduto.addActionListener(this); 

        menuBar.add(menuProduto);
        menuBar.add(menuCliente);
        menuBar.add(menuFornecedor);
        menuBar.add(menuVenda);
        menuBar.add(menuSobre);

        return menuBar;
    }

    private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminho);
        Image img = icon.getImage();
        Image novaImagem = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnVerProdutos) {
            listarProduto.doClick();
        } else if (evt.getSource() == btnComprarProduto) {
            adicionarVenda.doClick();
        }

        if (evt.getSource() == adicionarProduto) {
            ProdutoVisao produtoVisao = new ProdutoVisao();
            produtoVisao.setVisible(true);  
        }

        if (evt.getSource() == pesquisarProduto) {
            PesquisarPorNomeProduto.abrirJanela(this);;
        }

        if (evt.getSource() == adicionarVenda) {
            VendasInterface VendasInterface = new VendasInterface();
            VendasInterface.setVisible(true);  
        }

        if (evt.getSource() == pesquisarVenda) {
            PesquisarPorNomeVenda.abrirJanela(this);;
        }
        
        if (evt.getSource() == listarVenda){
            VendasDadosTable.listarVendas();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.setVisible(true);
        });
    }
}
