package src.pages.estoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.pages.produto.ProdutoModelo;

public class EstoqueInterface extends JFrame {
    private JComboBox<String> produtoComboBox;
    private JTextField quantidadeField, dataAtualizacaoField;
    private JButton salvarButton;
    private List<ProdutoModelo> produtos;

    public EstoqueInterface(List<ProdutoModelo> produtos) {
        this.produtos = produtos;
        setTitle("Gerenciamento de Estoque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        
        add(new JLabel("Produto:"));
        produtoComboBox = new JComboBox<>();
        for (ProdutoModelo produto : produtos) {
            produtoComboBox.addItem(produto.getNome());
        }
        add(produtoComboBox);

        add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        add(quantidadeField);

        add(new JLabel("Data de Atualização:"));
        dataAtualizacaoField = new JTextField();
        add(dataAtualizacaoField);

        salvarButton = new JButton("Salvar");
        add(salvarButton);
        
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarEstoque();
            }
        });
        
        setVisible(true);
    }

    private void salvarEstoque() {
        String produtoNome = (String) produtoComboBox.getSelectedItem();
        ProdutoModelo produtoSelecionado = produtos.stream()
            .filter(p -> p.getNome().equals(produtoNome))
            .findFirst()
            .orElse(null);
        
        if (produtoSelecionado != null) {
            try {
                int quantidade = Integer.parseInt(quantidadeField.getText());
                String dataAtualizacao = dataAtualizacaoField.getText();
                EstoqueModelo estoque = new EstoqueModelo(produtoSelecionado.getId(), quantidade, produtoSelecionado, dataAtualizacao);
                JOptionPane.showMessageDialog(this, "Estoque atualizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
