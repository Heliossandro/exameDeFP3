package src.pages.produto;

import javax.swing.*;
import Calendario.*;
import src.pages.CategoriaProduto.CategoriaProdutoModelo;
import src.pages.fornecedor.FornecedoresDadosTable;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProdutoVisao extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;

    public ProdutoVisao() {
        super("Registrar Novo Produto");

        getContentPane().setBackground(new Color(240, 240, 240)); // Fundo claro

        // Adiciona a imagem no topo, redimensionada
        JLabel imagemLabel = new JLabel(redimensionarImagem("C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/perfume.png", 100, 100));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(imagemLabel, BorderLayout.NORTH);


        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, quantidadeJTF, precoJTF, nomeJTF, marcaJTF, observacaoJTF;
        private JComboBox<String> categoriaJCB, fornecedorJCB;
        private List<CategoriaProdutoModelo> categoriasList;
        private JTextFieldData txtData;

        public PainelCentro() {
            setLayout(new GridBagLayout());
            setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            Font fonte = new Font("Arial", Font.PLAIN, 14);

            // Configuração do ID
            gbc.gridx = 0; gbc.gridy = 0;
            add(criarLabel("ID", fonte), gbc);
            gbc.gridx = 1;
            idJTF = criarTextField(10, false);
            ProdutoFile file = new ProdutoFile();
            idJTF.setText("" + file.getProximoCodigo());
            add(idJTF, gbc);

            // Nome
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Nome", fonte), gbc);
            gbc.gridx = 1;
            add(nomeJTF = criarTextField(15, true), gbc);

            // Marca
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Marca", fonte), gbc);
            gbc.gridx = 1;
            add(marcaJTF = criarTextField(15, true), gbc);

            // Quantidade
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Quantidade", fonte), gbc);
            gbc.gridx = 1;
            add(quantidadeJTF = criarTextField(10, true), gbc);

            // Preço
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Preço", fonte), gbc);
            gbc.gridx = 1;
            add(precoJTF = criarTextField(10, true), gbc);

            // Data
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Data", fonte), gbc);
            gbc.gridx = 1;
            JPanel painelData = new JPanel(new GridLayout(1,2));
            txtData  = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            painelData.setBackground(Color.WHITE);
            add(painelData, gbc);

            // Fornecedor
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Fornecedor", fonte), gbc);
            gbc.gridx = 1;
            fornecedorJCB = new JComboBox<>(FornecedoresDadosTable.getAllNodes());
            fornecedorJCB.setFont(fonte);
            add(fornecedorJCB, gbc);

            // Categoria
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Categoria", fonte), gbc);
            gbc.gridx = 1;
            categoriasList = CategoriaProdutoModelo.listarCategorias();
            String[] categoriasArray = categoriasList.isEmpty()
                    ? new String[]{"Nenhuma categoria encontrada"}
                    : categoriasList.stream().map(CategoriaProdutoModelo::getNome).toArray(String[]::new);
            categoriaJCB = new JComboBox<>(categoriasArray);
            categoriaJCB.setFont(fonte);
            add(categoriaJCB, gbc);

            // Observação
            gbc.gridx = 0; gbc.gridy++;
            add(criarLabel("Observação", fonte), gbc);
            gbc.gridx = 1;
            add(observacaoJTF = criarTextField(15, true), gbc);
        }

        private JLabel criarLabel(String texto, Font fonte) {
            JLabel label = new JLabel(texto);
            label.setFont(fonte);
            return label;
        }

        private JTextField criarTextField(int colunas, boolean editavel) {
            JTextField campo = new JTextField(colunas);
            campo.setFont(new Font("Arial", Font.PLAIN, 14));
            campo.setEditable(editavel);
            return campo;
        }

        public int getId() {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome() {
            return nomeJTF.getText().trim();
        }

        public String getMarca() {
            return marcaJTF.getText().trim();
        }

        public int getQuantidadeEmEstoque() {
            try {
                return Integer.parseInt(quantidadeJTF.getText().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public float getPreco() {
            try {
                return Float.parseFloat(precoJTF.getText().trim());
            } catch (NumberFormatException e) {
                return 0.0f;
            }
        }

        public String getData() {
            return txtData.getDTestField().getText();
        }

        public String getFornecedor() {
            return (String) fornecedorJCB.getSelectedItem();
        }

        public CategoriaProdutoModelo getCategoriaSelecionada() {
            String categoriaNome = String.valueOf(categoriaJCB.getSelectedItem());
            return categoriasList.stream()
                    .filter(c -> c.getNome().equals(categoriaNome))
                    .findFirst()
                    .orElse(new CategoriaProdutoModelo(0, "Desconhecida"));
        }

        public String getObservacao() {
            return observacaoJTF.getText().trim();
        }

        public boolean verificarCampos() {
            return !(getNome().isEmpty() || getMarca().isEmpty() ||
                    getQuantidadeEmEstoque() == 0 || getPreco() == 0.0f ||
                    getData().isEmpty() || getFornecedor().isEmpty() ||
                    getObservacao().isEmpty());
        }

        public void salvar() {
            ProdutoModelo modelo = new ProdutoModelo(
                getId(), 
                getQuantidadeEmEstoque(), 
                getPreco(), 
                getNome(),
                getMarca(), 
                getObservacao(),
                getFornecedor(),
                getData(),
                getCategoriaSelecionada()
            );

            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
            modelo.salvar();
            limparCampos();
        }

        public void limparCampos() {
            idJTF.setText("" + new ProdutoFile().getProximoCodigo()); // Gerar novo ID
            nomeJTF.setText("");
            marcaJTF.setText("");
            quantidadeJTF.setText("");
            precoJTF.setText("");
            txtData.getDTestField().setText("");
            fornecedorJCB.setSelectedIndex(0);
            categoriaJCB.setSelectedIndex(0);
            observacaoJTF.setText("");
        }
        
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton salvarJBT, cancelarJBT;

        public PainelSul() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            setBackground(new Color(220, 220, 220));

            salvarJBT = criarBotao("Salvar", new Color(0, 153, 76), "C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/certo.png");
            cancelarJBT = criarBotao("Cancelar", new Color(204, 0, 0), "C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/nao.png");

            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);

            add(salvarJBT);
            add(cancelarJBT);
        }

        private JButton criarBotao(String texto, ImageIcon icone) {
            JButton botao = new JButton(texto, icone);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setPreferredSize(new Dimension(140, 45));
            botao.setFocusPainted(false);
            return botao;
        }

        private JButton criarBotao(String texto, Color cor, String iconeCaminho) {
            JButton botao = new JButton(texto, redimensionarImagem(iconeCaminho, 20, 20));
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setForeground(Color.WHITE);
            botao.setBackground(cor);
            botao.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            botao.setFocusPainted(false);
            botao.setHorizontalTextPosition(SwingConstants.RIGHT); // Texto à direita do ícone

            botao.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor.darker());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    botao.setBackground(cor);
                }
            });

            botao.addActionListener(this);
            return botao;
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == salvarJBT) {
                if (centro.verificarCampos()) {
                    centro.salvar();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dispose();
            }
        }
    }

    private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon imagemOriginal = new ImageIcon(caminho);
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemRedimensionada);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProdutoVisao::new);
    }
}
