package src.pages.produto;

import javax.swing.*;
import src.pages.CategoriaProduto.CategoriaProdutoModelo;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProdutoVisao extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;

    public ProdutoVisao() {
        super("Registrar Novo Produto");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, quantidadeJTF, precoJTF;
        private JTextField nomeJTF, marcaJTF, dataValidadeJTF, observacaoJTF, fornecedorJTF;
        private JComboBox<String> categoriaJCB;
        private List<CategoriaProdutoModelo> categoriasList;

        public PainelCentro() {
            setLayout(new GridLayout(9, 2));

            // Linha 1
            add(new JLabel("ID"));
            add(idJTF = new JTextField());
            ProdutoFile file = new ProdutoFile();
            idJTF.setText("" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // Linha 2
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // Linha 3
            add(new JLabel("Marca"));
            add(marcaJTF = new JTextField());

            // Linha 4
            add(new JLabel("Quantidade em Estoque"));
            add(quantidadeJTF = new JTextField());

            // Linha 5
            add(new JLabel("Preço"));
            add(precoJTF = new JTextField());

            // Linha 6
            add(new JLabel("Data de Validade"));
            add(dataValidadeJTF = new JTextField());

            // Linha 7
            add(new JLabel("Fornecedor"));
            add(fornecedorJTF = new JTextField());

            // Linha 8 - Carregar categorias do arquivo
            add(new JLabel("Categoria"));

            // Carregar categorias do arquivo
            categoriasList = CategoriaProdutoModelo.listarCategorias();
            
            // Se a lista estiver vazia, usa um array padrão
            String[] categoriasArray;
            if (categoriasList.isEmpty()) {
                categoriasArray = new String[]{"Nenhuma categoria encontrada"};
            } else {
                categoriasArray = categoriasList.stream()
                                                .map(CategoriaProdutoModelo::getNome)
                                                .toArray(String[]::new);
            }

            // Criar JComboBox com categorias lidas
            categoriaJCB = new JComboBox<>(categoriasArray);
            add(categoriaJCB);

            // Linha 9
            add(new JLabel("Observação"));
            add(observacaoJTF = new JTextField());
        }

        public int getId() {
            return Integer.parseInt(idJTF.getText());
        }

        public String getNome() {
            return nomeJTF.getText().trim();
        }

        public String getMarca() {
            return marcaJTF.getText().trim();
        }

        public int getQuantidadeEmEstoque() {
            return Integer.parseInt(quantidadeJTF.getText().trim());
        }

        public float getPreco() {
            return Float.parseFloat(precoJTF.getText().trim());
        }

        public String getDataDeValidade() {
            return dataValidadeJTF.getText().trim();
        }

        public String getFornecedor() {
            return fornecedorJTF.getText().trim();
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

        public boolean isEmpty(Object valor) {
            return valor == null || valor.toString().trim().isEmpty();
        }

        public boolean verificarCampos() {
            return !(isEmpty(getId()) || isEmpty(getNome()) || isEmpty(getMarca()) ||
                    isEmpty(getQuantidadeEmEstoque()) || isEmpty(getPreco()) ||
                    isEmpty(getDataDeValidade()) || isEmpty(getFornecedor()) ||
                    isEmpty(getCategoriaSelecionada()) || isEmpty(getObservacao()));
        }

        public void salvar() {
            ProdutoModelo modelo = new ProdutoModelo(getId(), getNome(), getMarca(), getQuantidadeEmEstoque(),
                    getPreco(), getDataDeValidade(), getFornecedor(), getCategoriaSelecionada(), getObservacao());

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton salvarJBT, cancelarJBT;

        public PainelSul() {
            setLayout(new FlowLayout());
            add(salvarJBT = new JButton("Salvar"));
            add(cancelarJBT = new JButton("Cancelar"));
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == salvarJBT) {
                if (centro.verificarCampos()) {
                    centro.salvar();
                } else {
                    JOptionPane.showMessageDialog(null, "Campos vazios", "Verificador de Campos", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new ProdutoVisao();
    }
}
