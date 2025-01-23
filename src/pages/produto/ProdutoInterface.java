package src.pages.produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import src.models.ProdutoModelo;

public class ProdutoInterface extends JFrame {

    private JTextField nomeField, marcaField, precoField, quantidadeField, validadeField, observacaoField;
    private JComboBox<String> categoriaComboBox, fornecedorComboBox;

    public ProdutoInterface() {
        setTitle("Gestão de Produtos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        configurarLayout();
    }

    private void configurarLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Labels e campos de entrada
        mainPanel.add(createLabel("Nome do Produto:", labelFont));
        nomeField = new JTextField();
        styleTextField(nomeField);
        mainPanel.add(nomeField);

        mainPanel.add(createLabel("Marca:", labelFont));
        marcaField = new JTextField();
        styleTextField(marcaField);
        mainPanel.add(marcaField);

        mainPanel.add(createLabel("Preço:", labelFont));
        precoField = new JTextField();
        styleTextField(precoField);
        mainPanel.add(precoField);

        mainPanel.add(createLabel("Quantidade em Estoque:", labelFont));
        quantidadeField = new JTextField();
        styleTextField(quantidadeField);
        mainPanel.add(quantidadeField);

        mainPanel.add(createLabel("Data de Validade (DD/MM/AAAA):", labelFont));
        validadeField = new JTextField();
        styleTextField(validadeField);
        mainPanel.add(validadeField);

        mainPanel.add(createLabel("Fornecedor:", labelFont));
        fornecedorComboBox = new JComboBox<>(new String[]{"Fornecedor A", "Fornecedor B", "Fornecedor C", "Fornecedor D"});
        styleComboBox(fornecedorComboBox);
        mainPanel.add(fornecedorComboBox);

        mainPanel.add(createLabel("Categoria:", labelFont));
        categoriaComboBox = new JComboBox<>(new String[]{"Perfume", "Creme", "Óleo", "Conjunto"});
        styleComboBox(categoriaComboBox);
        mainPanel.add(categoriaComboBox);

        mainPanel.add(createLabel("Observação:", labelFont));
        observacaoField = new JTextField();
        styleTextField(observacaoField);
        mainPanel.add(observacaoField);

        // Botão para salvar o produto
        JButton salvarButton = new JButton("Salvar Produto");
        styleButton(salvarButton);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        mainPanel.add(salvarButton);

        // Espaço vazio para alinhamento
        mainPanel.add(new JLabel());

        add(mainPanel);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.DARK_GRAY);
        return label;
    }

    private void styleTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        comboBox.setFocusable(false);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 120, 215));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Efeito hover no botão
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 100, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 215));
            }
        });
    }

    private void salvarProduto() {
        try {
            // Gera ID aleatório
            Random random = new Random();
            int id = random.nextInt(1000) + 1;

            // Obtém os valores do formulário
            String nome = nomeField.getText();
            String marca = marcaField.getText();
            double preco = Double.parseDouble(precoField.getText());
            int quantidade = Integer.parseInt(quantidadeField.getText());
            String validade = validadeField.getText();
            String fornecedor = (String) fornecedorComboBox.getSelectedItem();
            String categoria = (String) categoriaComboBox.getSelectedItem();
            String observacao = observacaoField.getText();

            // Cria o produto
            ProdutoModelo produto = new ProdutoModelo(
                    id, quantidade, preco, nome, marca, categoria, validade, observacao, fornecedor
            );

            // Mostra uma mensagem de sucesso
            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado com sucesso!\n" + produto,
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Limpa os campos do formulário
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o produto. Verifique os campos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        marcaField.setText("");
        precoField.setText("");
        quantidadeField.setText("");
        validadeField.setText("");
        observacaoField.setText("");
        categoriaComboBox.setSelectedIndex(0);
        fornecedorComboBox.setSelectedIndex(0);
    } 
}
