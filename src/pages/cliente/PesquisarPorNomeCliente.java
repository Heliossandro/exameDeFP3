package src.pages.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeCliente extends JDialog {
    private JTextField campoNome;
    private JButton botaoPesquisar, botaoFechar;

    public PesquisarPorNomeCliente(Frame parent) {
        super(parent, "Pesquisar Cliente", true);
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
                pesquisarPorNomeCliente(campoNome.getText().trim());
            }
        });

        botaoFechar.addActionListener(e -> dispose());

        add(new JLabel("Digite o nome do cliente:"));
        add(campoNome);
        add(botaoPesquisar);
        add(botaoFechar);
    }

    private void pesquisarPorNomeCliente(String nomeProcurado) {
        if (nomeProcurado.isEmpty()) {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
            return;
        }

        ClientesDadosTable tabelaClientes = new ClientesDadosTable("clientes.DAT", 100);

        try {
            ClientesPNode cliente = tabelaClientes.getNode(nomeProcurado);

            if (cliente != null && !cliente.isEmptyNode()) {
                String mensagem = "Cliente encontrado:\n" + cliente.getModel().toString();
                JOptionPane.showMessageDialog(this, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("Cliente não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar cliente: " + e.getMessage(), "Erro", true);
        }
    }

    public static void abrirJanela(Frame parent) {
        PesquisarPorNomeCliente dialog = new PesquisarPorNomeCliente(parent);
        dialog.setVisible(true);
    }
}
