package src.pages.documento;

import src.models.DocumentoModelo;
import src.DAO.AdicionarDocmentosDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DocumentoInterface extends JFrame {
    private PainelSul sul;
    private PainelCentro centro;
    private static int contadorId;
    private AdicionarDocmentosDAO dao = new AdicionarDocmentosDAO();

    public DocumentoInterface() {
        super("Adicionar Documento");

        // Recuperar o próximo ID com base nos documentos existentes
        contadorId = recuperarUltimoId();

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int recuperarUltimoId() {
        List<DocumentoModelo> documentos = dao.getAll();
        int maxId = 0;
        for (DocumentoModelo doc : documentos) {
            if (doc.getId() > maxId) {
                maxId = doc.getId();
            }
        }
        return maxId + 1; // O próximo ID será o maior encontrado + 1
    }

    class PainelCentro extends JPanel {
        private JTextField txtId;
        private JTextField txtTipoDoDocumento;

        public PainelCentro() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            setBackground(new Color(245, 245, 245));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Campo ID
            JLabel lblId = new JLabel("ID:");
            lblId.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(lblId, gbc);

            txtId = new JTextField(String.valueOf(contadorId));
            txtId.setEditable(false);
            txtId.setFont(new Font("Arial", Font.BOLD, 14));
            txtId.setHorizontalAlignment(JTextField.CENTER);
            txtId.setPreferredSize(new Dimension(200, 30));
            gbc.gridx = 1;
            gbc.gridy = 0;
            add(txtId, gbc);

            // Campo Documento
            JLabel lblDocumento = new JLabel("Documento:");
            lblDocumento.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(lblDocumento, gbc);

            txtTipoDoDocumento = new JTextField();
            txtTipoDoDocumento.setFont(new Font("Arial", Font.PLAIN, 14));
            txtTipoDoDocumento.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            txtTipoDoDocumento.setPreferredSize(new Dimension(200, 30));
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(txtTipoDoDocumento, gbc);
        }

        public String getDocumento() {
            return txtTipoDoDocumento.getText();
        }

        public int getId() {
            return Integer.parseInt(txtId.getText());
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            setBackground(new Color(245, 245, 245));

            btnSalvar = new JButton("Salvar");
            estilizarBotao(btnSalvar);
            add(btnSalvar);

            btnCancelar = new JButton("Cancelar");
            estilizarBotao(btnCancelar);
            add(btnCancelar);

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        private void estilizarBotao(JButton botao) {
            botao.setFocusPainted(false);
            botao.setBackground(new Color(0, 120, 215));
            botao.setForeground(Color.WHITE);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

            botao.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    botao.setBackground(new Color(0, 100, 180));
                }

                public void mouseExited(MouseEvent evt) {
                    botao.setBackground(new Color(0, 120, 215));
                }
            });
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar) {
                String documento = centro.getDocumento();
                int id = centro.getId();

                if (documento.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo Documento não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DocumentoModelo documentoModelo = new DocumentoModelo(id, documento);
                dao.save(documentoModelo);

                JOptionPane.showMessageDialog(null, "Documento salvo com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Atualizar o ID e limpar o campo de texto
                contadorId = recuperarUltimoId();
                centro.txtId.setText(String.valueOf(contadorId));
                centro.txtTipoDoDocumento.setText("");

            } else {
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new DocumentoInterface();
    }
}

