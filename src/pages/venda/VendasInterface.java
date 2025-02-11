package src.pages.venda;

import javax.swing.*;
import Calendario.JTextFieldData;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.pages.cliente.ClientesDadosTable;
import src.pages.produto.ProdutosDadosTable;

public class VendasInterface extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;

    public VendasInterface() {
        super("Registrar Vendas");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, quantidadeJTF, precoJTF;
        private JComboBox<String> produtoJCB, clienteJCB;
        private JTextFieldData txtData;

        public PainelCentro() {
            setLayout(new GridLayout(7, 2));

            add(new JLabel("ID"));
            add(idJTF = new JTextField());
            VendaFile file = new VendaFile();
            idJTF.setText(String.valueOf(file.getProximoCodigo()));
            idJTF.setFocusable(false);

            add(new JLabel("Cliente"));
            clienteJCB = new JComboBox<>(ClientesDadosTable.getAllNodes());
            add(clienteJCB);

            add(new JLabel("Produto"));
            produtoJCB = new JComboBox<>(ProdutosDadosTable.getAllNodes());
            add(produtoJCB);

            add(new JLabel("Quantidade"));
            add(quantidadeJTF = new JTextField());

            add(new JLabel("Preço"));
            add(precoJTF = new JTextField());

            add(new JLabel("Data"));
            JPanel painelData = new JPanel(new GridLayout(1, 2));
            txtData = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            add(painelData);
        }

        public int getId() {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public int getQuantidade() {
            try {
                return Integer.parseInt(quantidadeJTF.getText().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        public double getPreco() {
            try {
                return Double.parseDouble(precoJTF.getText().trim());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }

        public String getData() {
            return txtData.getDTestField().getText();
        }

        public String getCliente() {
            return (String) clienteJCB.getSelectedItem();
        }

        public String getProduto() {
            return (String) produtoJCB.getSelectedItem();
        }

        public boolean verificarCampos() {
            return !(getQuantidade() == 0 || getPreco() == 0.0 || getCliente() == null || getProduto() == null || getData().isEmpty());
        }

        public void salvar() {
            try {
                VendaModelo venda = new VendaModelo(
                    getId(),
                    getQuantidade(),
                    getData(),
                    getProduto(),
                    getCliente(),
                    "Dinheiro",  // Método de pagamento fixo por enquanto
                    getPreco()
                );

                JOptionPane.showMessageDialog(null, 
                    "Venda salva com sucesso!\n\n" +
                    "ID: " + venda.getId() + "\n" +
                    "Produto: " + venda.getProduto() + "\n" +
                    "Cliente: " + venda.getCliente() + "\n" +
                    "Quantidade: " + venda.getQuantidade() + "\n" +
                    "Preço: " + venda.getPreco() + "\n" +
                    "Total: " + venda.getTotal() + "\n" +
                    "Data: " + venda.getDataVenda() + "\n"
                );

                System.out.println("Enviando os seguintes dados para o banco de dados...");
                System.out.println(venda.toString());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
                    JOptionPane.showMessageDialog(null, "Campos vazios ou inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new VendasInterface();
    }
}
