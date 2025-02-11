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

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, quantidadeJTF, precoJTF;
        private JTextField nomeJTF, marcaJTF, observacaoJTF;
        private JComboBox<String> categoriaJCB;
        private List<CategoriaProdutoModelo> categoriasList;
        private JComboBox<String> fornecedorJCB;
        private JTextFieldData txtData;

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
            add(new JLabel("Data "));
            JPanel painelData = new JPanel(new GridLayout(1,2));
            txtData  = new JTextFieldData("Data?");
			painelData.add( txtData.getDTestField() );
			painelData.add( txtData.getDButton() );
			add(painelData);

            // Linha 7
            add(new JLabel("Fornecedor"));
            fornecedorJCB = new JComboBox<>(FornecedoresDadosTable.getAllNodes());
            add(fornecedorJCB);

            // Linha 8 - Carregar categorias do arquivo
            add(new JLabel("Categoria"));

            // Carregar categorias do arquivo
            categoriasList = CategoriaProdutoModelo.listarCategorias();

            // Se a lista estiver vazia, usa um array padrão
            String[] categoriasArray = categoriasList.isEmpty()
                    ? new String[]{"Nenhuma categoria encontrada"}
                    : categoriasList.stream()
                                    .map(CategoriaProdutoModelo::getNome)
                                    .toArray(String[]::new);

            // Criar JComboBox com categorias lidas
            categoriaJCB = new JComboBox<>(categoriasArray);
            add(categoriaJCB);

            // Linha 9
            add(new JLabel("Observação"));
            add(observacaoJTF = new JTextField());
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
                return 0; // Retorna 0 se o campo estiver vazio ou inválido
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

        public boolean isEmpty(String valor) {
            return valor == null || valor.trim().isEmpty();
        }

        public boolean verificarCampos() {
            return !(isEmpty(getNome()) || isEmpty(getMarca()) ||
                    getQuantidadeEmEstoque() == 0 || getPreco() == 0.0f ||
                    isEmpty(getData()) || isEmpty(getFornecedor()) ||
                    isEmpty(getObservacao()));
        }

        public void salvar() {
            ProdutoModelo modelo = new ProdutoModelo(
                getId(), 
                getQuantidadeEmEstoque(), 
                getPreco(), 
                getNome(),
                getMarca(), 
                getObservacao(), // Ordem correta
                getFornecedor(),
                getData(), // Passar data apenas uma vez
                getCategoriaSelecionada()
            );

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