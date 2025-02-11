package src.pages.fornecedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeFornecedor extends JDialog {
    private JTextField campoNome;
    private JButton botaoPesquisar, botaoFechar;

    public PesquisarPorNomeFornecedor(Frame parent) {
        super(parent, "Pesquisar Fornecedor", true);
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
                pesquisarPorNomeFornecedor(campoNome.getText().trim());
            }
        });

        botaoFechar.addActionListener(e -> dispose());

        add(new JLabel("Digite o nome do Fornecedor:"));
        add(campoNome);
        add(botaoPesquisar);
        add(botaoFechar);
    }

    private void pesquisarPorNomeFornecedor(String nomeProcurado) {
        if (nomeProcurado.isEmpty()) {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
            return;
        }

        FornecedoresDadosTable tabelaFornecedors = new FornecedoresDadosTable("Fornecedors.DAT", 100);

        try {
            FornecedoresPNode fornecedor = tabelaFornecedors.getNode(nomeProcurado);

            if (fornecedor != null && !fornecedor.isEmptyNode()) {
                String mensagem = "Fornecedor encontrado:\n" + fornecedor.getModel().toString();
                JOptionPane.showMessageDialog(this, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("Fornecedor não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar Fornecedor: " + e.getMessage(), "Erro", true);
        }
    }

    public static void abrirJanela(Frame parent) {
        PesquisarPorNomeFornecedor dialog = new PesquisarPorNomeFornecedor(parent);
        dialog.setVisible(true);
    }
}
