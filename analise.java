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
 - ApresentacaoInterface
 - Login   
 - MenuPrincipal
 - ProdutosInterface 
 - ClientesInterface 
 - VendasInterface 
 - EstoqueInterface 

3. Entidades Fortes e Seus Atributos (Modelo)

   ProdutosModelo
   - int id
   - String nome
   - String marca
   - double preco
   - String categoria
   - int quantidadeEmEstoque
   - String dataDeValidade
   - String observacao
   - String fornecidor

   ClientesModelo
   - int id
   - String nome
   - String numTelefone
   - String email
   - char genero
   - String dataDeNascimento

   VendasModelo
   - int id
   - ProdutosModelo produto
   - ClientesModelo cliente
   - FuncionariosModelo funcionario
   - int quantidade
   - double total
   - String dataVenda
   - String metodoDePagamento

   EstoqueModelo
   - int id
   - ProdutosModelo produto
   - int quantidade
   - String dataAtualizacao

4. Ficheiro

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
  - CategoriaProdutos.tab 
  - MetodoDePagamento.tab
  - CargoFuncionarios.tab
  - Fornecidores.tab

6. Listagens e Pesquisas
   - Listar produtos por categoria
   - Listar vendas por período
   - Pesquisar cliente por nome ou telefone
   - Listar fornecedores por tipo de produto****
   - Pesquisar funcionário por cargo***
*/
