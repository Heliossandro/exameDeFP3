package src.pages.produto;

import javax.swing.*;
import src.components.utils.ShowMessage;

public class PesquisarPorNomeProduto {
    public static void pesquisarPorNomeProduto(String nomeProcurado) {
       ProdutosDadosTable tabelaprodutos = new ProdutosDadosTable("produtos.DAT", 100);
        
        try {
           ProdutosPNode produto = tabelaprodutos.getNode(nomeProcurado);
            
            if (produto != null && !produto.isEmptyNode()) {
                String mensagem = "produto encontrado:\n" + produto.getModel().toString();
                JOptionPane.showMessageDialog(null, mensagem, "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ShowMessage.displayMessage("produto não encontrado!", "Aviso", false);
            }
        } catch (Exception e) {
            ShowMessage.displayMessage("Erro ao pesquisar produto: " + e.getMessage(), "Erro", true);
        }
    }
    
    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto para pesquisar:");
        if (nome != null && !nome.trim().isEmpty()) {
            pesquisarPorNomeProduto(nome.trim());
        } else {
            ShowMessage.displayMessage("Nome inválido!", "Aviso", false);
        }
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}
