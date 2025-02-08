package src.pages.cliente;

import src.models.ClienteModelo;
import src.models.DocumentoModelo;
import src.DAO.AdicionarDocumentosDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClienteInterface extends JFrame {
    private PainelSul sul;
    private static int contadorId;
    private PainelCentro centro;
    
    public ClienteInterface() {
        super("Cadastrar Clientes");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(450, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {

        private JTextField txtId, txtNome, txtNumTelefone, txtEmail;
        private JComboBox<String> cbGenero;
        private JComboBox<String> cbDocumentos;

        public PainelCentro() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;


            // ID
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(createLabel("ID:"), gbc);
            gbc.gridx = 1;
            txtId = createTextField();
            txtId.setEditable(false); // Para evitar edição manual
            txtId.setText(String.valueOf(ClienteDadosTable.getNextID())); // Certifique-se de que ClienteDadosTable existe
            add(txtId, gbc);

            // Nome
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(createLabel("Nome:"), gbc);
            gbc.gridx = 1;
            txtNome = createTextField();
            add(txtNome, gbc);

            // Número de telefone
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(createLabel("Número de Telefone:"), gbc);
            gbc.gridx = 1;
            txtNumTelefone = createTextField();
            add(txtNumTelefone, gbc);

            // Email
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(createLabel("E-mail:"), gbc);
            gbc.gridx = 1;
            txtEmail = createTextField();
            add(txtEmail, gbc);

            // Gênero (ComboBox)
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(createLabel("Gênero:"), gbc);
            gbc.gridx = 1;
            String[] opcoesGenero = {"Masculino", "Feminino"};
            cbGenero = createComboBox(opcoesGenero);
            add(cbGenero, gbc);

            // Documento (ComboBox)
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(createLabel("Documento:"), gbc);
            gbc.gridx = 1;
            cbDocumentos = createComboBox(carregarDocumentos());
            add(cbDocumentos, gbc);
        }

        private String[] carregarDocumentos() {
            AdicionarDocumentosDAO dao = new AdicionarDocumentosDAO();
            List<DocumentoModelo> documentos = dao.getAll();
            return documentos.stream().map(DocumentoModelo::getNome).toArray(String[]::new);
        }

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            return label;
        }

        private JTextField createTextField() {
            JTextField textField = new JTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 14));
            textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            textField.setPreferredSize(new Dimension(200, 30));
            return textField;
        }

        private JComboBox<String> createComboBox(String[] items) {
            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
            comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            return comboBox;
        }

        public int getId()
		{
			return Integer.parseInt( txtId.getText() );
		}
        public String getNome() {
            return txtNome.getText().trim();
        }

        public String getNumTelefone() {
            return txtNumTelefone.getText().trim();
        }

        public String getEmail() {
            return txtEmail.getText().trim();
        }

        public char getGenero() {
            return cbGenero.getSelectedItem().toString().charAt(0);
        }

        public String getDocumento() {
            return cbDocumentos.getSelectedItem().toString();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            setBackground(Color.WHITE);

            btnSalvar = createButton("Salvar", new Color(0, 120, 215));
            add(btnSalvar);

            btnCancelar = createButton("Cancelar", new Color(200, 0, 0));
            add(btnCancelar);
        }

        private JButton createButton(String text, Color color) {
            JButton botao = new JButton(text);
            botao.setFocusPainted(false);
            botao.setBackground(color);
            botao.setForeground(Color.WHITE);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
            botao.addActionListener(this);
            return botao;
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar) {
                String nome = centro.getNome();
                String telefone = centro.getNumTelefone();
                String email = centro.getEmail();
                char genero = centro.getGenero();
                String documento = centro.getDocumento();

                JOptionPane.showMessageDialog(null,
                        "Nome: " + nome +
                        "\nTelefone: " + telefone +
                        "\nE-mail: " + email +
                        "\nGênero: " + genero +
                        "\nDocumento: " + documento,
                        "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
                        salvar();
            } else {
                dispose();
            }
        }

        public void salvar() {
            
            int id = centro.getId();
            String nome = centro.getNome();
            String telefone = centro.getNumTelefone();
            String email = centro.getEmail();
            String genero = String.valueOf(centro.getGenero());
        
            ClienteModelo modelo = new ClienteModelo(id, nome, telefone, email, genero);
        
            // Salvando os dados
            modelo.salvar();
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
        } 

       
    }

    public static void main(String args[]) {
        new ClienteInterface();
    }
}
