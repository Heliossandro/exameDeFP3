package src.pages.cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class ListarClientes extends JFrame {
    private static final Logger logger = Logger.getLogger(ListarClientes.class.getName());
    private JTable tabela;
    private DefaultTableModel modelo;
    private ClientesDadosTable tabelaClientes;

    public ListarClientes() {
        setTitle("Lista de Clientes");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if (!verificarArquivo("CLIENTE.DAT")) {
            JOptionPane.showMessageDialog(this, "Arquivo de clientes não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tabelaClientes = new ClientesDadosTable("clientes.DAT", 100);
        
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Telefone", "Gênero", "Email"}, 0);
        tabela = new JTable(modelo);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(200);

        carregarClientes();

        JPanel panel = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void carregarClientes() {
        modelo.setRowCount(0);
        List<ClientesPNode> clientes = tabelaClientes.getAllNodes();
    
        if (clientes.isEmpty()) {
            logger.warning("Nenhum cliente encontrado no arquivo.");
        } else {
            logger.info("Clientes carregados: " + clientes.size());
        }
    
        for (ClientesPNode cliente : clientes) {
            String id = limparTexto(String.valueOf(cliente.getModel().getId()));
            String nome = limparTexto(cliente.getModel().getNome());
            String telefone = limparTexto(cliente.getModel().getNumTelefone());
            String genero = limparTexto(cliente.getModel().getGenero());
            String email = limparTexto(cliente.getModel().getEmail());
    
            logger.info("ID: '" + id + "' Nome: '" + nome + "' Telefone: '" + telefone + "' Gênero: '" + genero + "' Email: '" + email + "'");
            
            modelo.addRow(new Object[]{id, nome, telefone, genero, email});
        }
    }

    private String limparTexto(String texto) {
        return texto == null ? "" : texto.trim().replaceAll("[^\\p{Print}]", "");
    }

    private boolean verificarArquivo(String caminho) {
        File arquivo = new File(caminho);
        return arquivo.exists() && arquivo.canRead();
    }
       
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListarClientes().setVisible(true));
    }
}