package src.pages.cliente;

import javax.swing.*;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeCliente {
    public static void pesquisarPorNomeCliente(String nomeProcurado) {
        ClientesDadosTable tabelaClientes = new ClientesDadosTable("clientes.DAT", 100);
        
        try {
            ClientesPNode cliente = tabelaClientes.getNode(nomeProcurado);
            
            if (cliente != null && !cliente.isEmptyNode()) {
                String mensagem = "Cliente encontrado:\n" +
                                  cliente.getModel().toString();
                JOptionPane.showMessageDialog(null, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("Cliente não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar cliente: " + e.getMessage(), "Erro", true);
        }
    }
    
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente para pesquisar:");
        if (nome != null && !nome.trim().isEmpty()) {
            pesquisarPorNomeCliente(nome.trim());
        } else {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
        }
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}
