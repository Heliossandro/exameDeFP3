package src.pages.venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import src.pages.cliente.ClientesDadosTable;
import src.pages.produto.ProdutosDadosTable;

public class VendasInterface extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;

    public VendasInterface() {
        super("Registrar Vendas");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, quantidadeJTF, precoJTF, totalJTF, dataJTF;
        private JComboBox<String> produtoJCB, clienteJCB;

        public PainelCentro() {
            setLayout(new GridLayout(8, 2));

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
            quantidadeJTF = new JTextField();
            add(quantidadeJTF);

            add(new JLabel("Preço"));
            precoJTF = new JTextField();
            add(precoJTF);

            add(new JLabel("Total"));
            totalJTF = new JTextField();
            totalJTF.setEditable(false); // O total será calculado automaticamente
            add(totalJTF);

            add(new JLabel("Data"));
            dataJTF = new JTextField(getDataAtual());
            dataJTF.setEditable(false); // A data será preenchida automaticamente
            add(dataJTF);

            // Atualiza o total sempre que a quantidade ou o preço forem alterados
            DocumentListener updateTotalListener = new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { calcularTotal(); }
                public void removeUpdate(DocumentEvent e) { calcularTotal(); }
                public void changedUpdate(DocumentEvent e) { calcularTotal(); }
            };
            quantidadeJTF.getDocument().addDocumentListener(updateTotalListener);
            precoJTF.getDocument().addDocumentListener(updateTotalListener);
        }

        // Método para obter a data atual no formato correto
        private String getDataAtual() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(new Date());
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

        public double getTotal() {
            return getQuantidade() * getPreco();
        }

        public String getData() {
            return dataJTF.getText();
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

        public void calcularTotal() {
            double total = getTotal();
            totalJTF.setText(String.format("%.2f", total));
        }

        public void salvar() {
            VendaModelo venda = new VendaModelo(
                getId(),
                getQuantidade(),
                getData(),
                getProduto(),
                getCliente(),
                "Dinheiro",
                getPreco(),
                getTotal() // Novo campo total sendo enviado
            );
            JOptionPane.showMessageDialog(null, venda.toString());

            venda.salvar();
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
