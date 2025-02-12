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
        
         getContentPane().setBackground(new Color(240, 240, 240)); // Fundo claro

        // Adiciona a imagem no topo, redimensionada
        JLabel imagemLabel = new JLabel(redimensionarImagem("C:/Users/Heliossandro Afonso/Documents/Aulas/FPIII/projeto_exame/image/clientes.png", 100, 100));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(imagemLabel, BorderLayout.NORTH);


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
            limparCampos();
        }

        public void limparCampos() {
            idJTF.setText(String.valueOf(new VendaFile().getProximoCodigo())); // Atualiza o ID
            quantidadeJTF.setText("");
            precoJTF.setText("");
            totalJTF.setText("");
            dataJTF.setText(getDataAtual()); // Atualiza a data
            clienteJCB.setSelectedIndex(0);
            produtoJCB.setSelectedIndex(0);
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
                    JOptionPane.showMessageDialog(null, "Campos vazios ou inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        new VendasInterface();
    }
}
