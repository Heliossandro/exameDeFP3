package src.pages.venda;

import javax.swing.*;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeVenda {
    public static void pesquisarPorNomevenda(String nomeProcurado) {
       VendasDadosTable tabelavendas = new VendasDadosTable("vendas.DAT", 100);
        
        try {
           VendasPNode venda = tabelavendas.getNode(nomeProcurado);
            
            if (venda != null && !venda.isEmptyNode()) {
                String mensagem = "venda encontrado:\n" + venda.getModel().toString();
                JOptionPane.showMessageDialog(null, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("venda não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar venda: " + e.getMessage(), "Erro", true);
        }
    }
    
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do venda para pesquisar:");
        if (nome != null && !nome.trim().isEmpty()) {
            pesquisarPorNomevenda(nome.trim());
        } else {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
        }
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}
