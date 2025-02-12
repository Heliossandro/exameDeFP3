package src.pages.venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeVenda extends JDialog {
    private JTextField campoNome;
    private JButton botaoPesquisar, botaoFechar;

    public PesquisarPorNomeVenda(Frame parent) {
        super(parent, "Pesquisar Venda", true);
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
                pesquisarPorNomeVenda(campoNome.getText().trim());
            }
        });

        botaoFechar.addActionListener(e -> dispose());

        add(new JLabel("Digite o nome do Venda:"));
        add(campoNome);
        add(botaoPesquisar);
        add(botaoFechar);
    }

    private void pesquisarPorNomeVenda(String nomeProcurado) {
        if (nomeProcurado.isEmpty()) {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
            return;
        }

        VendasDadosTable tabelaVendas = new VendasDadosTable("vendaes.DAT", 100);

        try {
            VendasPNode venda = tabelaVendas.getNode(nomeProcurado);

            if (venda != null && !venda.isEmptyNode()) {
                String mensagem = "venda encontrado:\n" + venda.getModel().toString();
                JOptionPane.showMessageDialog(this, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("venda não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar venda: " + e.getMessage(), "Erro", true);
        }
    }

    public static void abrirJanela(Frame parent) {
        PesquisarPorNomeVenda dialog = new PesquisarPorNomeVenda(parent);
        dialog.setVisible(true);
    }
}
