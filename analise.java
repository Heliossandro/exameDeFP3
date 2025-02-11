/*------------------------------------
Tema: Gestão de uma Perfumaria
Nome: Heliossandro Afonso
Número: 947024057
Ficheiro: Analise.java
Data: 13.01.2025
--------------------------------------*/

/*
1. Objectivo: criar um sistema capaz de gerir os processos operacionais de uma perfumaria, incluindo o controle de estoque, vendas, fluxo de clientes, funcionários e fornecedores, permitindo a otimização da entrada e saída de produtos e dados.

2. Visão [Interfaces Gráficas]
 - Login   
 - MenuPrincipal
 - ProdutosInterface 
 - FuncionariosInterface 
 - ClientesInterface 
 - VendasInterface
 - FornecedoresInterface

3. Entidades Fortes e Seus Atributos (Modelo)

   ProdutosModelo
   - int id
   - String nome
   - String marca
   - double preco
   - String categoria (Perfume, Creme, Óleo, etc.)
   - int quantidadeEmEstoque
   - String dataDeValidade
   - String observacao
   - CategoriaProdutos categoriaProduto
   - fornecedorModelo fornecedor

   ClientesModelo
   - int id
   - String nome
   - String numTelefone
   - String email
   - char genero
   - Documento documento;

   FuncionariosModelo
   - int id
   - String nome
   - String cargo
   - String senha
   - String numTelefone
   - char genero
   - String dataDeAdmissao
   - Documento documento

   VendasModelo
   - int id
   - ProdutosModelo produto
   - ClientesModelo cliente
   - FuncionariosModelo funcionario
   - int quantidade
   - double total
   - String dataVenda
   - String metodoDePagamento

   FornecedoresModelo
   - int id
   - String nome
   - String contato
   - String endereco

4. Ficheiro

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
  - CategoriaProdutos.tab 
  - Documento.tab
*/
