package src.pages.produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeProduto extends JDialog {
    private JTextField campoNome;
    private JButton botaoPesquisar, botaoFechar;

    public PesquisarPorNomeProduto(Frame parent) {
        super(parent, "Pesquisar produto", true);
        configurarUI();
    }

    private void configurarUI() {
        setLayout(new GridLayout(3, 1, 10, 10));
        setSize(400, 200);
        setLocationRelativeTo(null);

        campoNome = new JTextField();
        botaoPesquisar = new JButton("Pesquisar");
        botaoFechar = new JButton("Fechar");

        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarPorNomeProduto(campoNome.getText().trim());
            }
        });

        botaoFechar.addActionListener(e -> dispose());

        add(new JLabel("Digite o nome do produto:"));
        add(campoNome);
        add(botaoPesquisar);
        add(botaoFechar);
    }

    private void pesquisarPorNomeProduto(String nomeProcurado) {
        if (nomeProcurado.isEmpty()) {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
            return;
        }

        ProdutosDadosTable tabelaProdutos = new ProdutosDadosTable("produtos.DAT", 100);

        try {
            ProdutosPNode produto = tabelaProdutos.getNode(nomeProcurado);

            if (produto != null && !produto.isEmptyNode()) {
                String mensagem = "produto encontrado:\n" + produto.getModel().toString();
                JOptionPane.showMessageDialog(this, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("produto não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar produto: " + e.getMessage(), "Erro", true);
        }
    }

    public static void abrirJanela(Frame parent) {
        PesquisarPorNomeProduto dialog = new PesquisarPorNomeProduto(parent);
        dialog.setVisible(true);
    }
}
