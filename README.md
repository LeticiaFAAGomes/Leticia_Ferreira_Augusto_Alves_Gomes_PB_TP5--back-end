# JSports - Back-end

Este repositório contém o código fonte do projeto de back-end **JSports**, desenvolvido por mim Letícia, para o Teste de Performance 5 (TP5) da disciplina de Projeto de Bloco de Back-end. O projeto simula um sistema de gerenciamento de pedidos, com funcionalidades para clientes e vendedores.

## 🚀 Tecnologias Utilizadas

O projeto é construído com as seguintes tecnologias:

- **Java 24**: Linguagem de programação principal.
- **Spring Boot 3.5.4**: Framework para construção de aplicações Java robustas e escaláveis.
- **Spring Web**: Para construção de APIs RESTful.
- **Spring Data JPA**: Para persistência de dados e interação com o banco de dados.
- **Spring Security**: Para autenticação e autorização de usuários.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Spring Boot DevTools**: Ferramentas para agilizar o desenvolvimento.
- **Springdoc OpenAPI (Swagger UI)**: Para documentação automática da API e interface de teste.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.

## ✨ Funcionalidades Principais

O sistema JSports oferece as seguintes funcionalidades:

### **Autenticação e Usuários**

- **Cadastro de Usuário**: Permite o registro de novos usuários como `CLIENTE` ou `VENDEDOR`.
- **Login**: Autenticação de usuários com e-mail e senha.
- **Listagem de Usuários por Tipo**: Retorna usuários filtrados por `CLIENTE` ou `VENDEDOR`.

### **Itens Esportivos (Produtos)**

- **Cadastro de Item Esportivo**: Vendedores podem cadastrar novos produtos.
- **Listagem de Itens**: Clientes podem visualizar produtos do vendedor.
- **Listagem de Itens por Categoria**: Clientes podem filtrar produtos por categorias como `TRAJES_ESPORTIVOS`, `EQUIPAMENTOS_TREINO`, `OUTDOOR`, `ACESSORIOS_DE_PROTECAO`.
- **Reposição de Estoque**: Vendedores podem adicionar quantidade ao estoque de um item existente.
- **Alteração de Preço**: Vendedores podem atualizar o preço de um item.

### **Pedidos e Compras**

- **Adicionar ao Carrinho**: Clientes podem adicionar itens ao carrinho de compras.
- **Remover do Carrinho**: Clientes podem remover itens do carrinho.
- **Realizar Compra**: Clientes podem finalizar a compra dos itens no carrinho, gerando um pedido.
- **Cancelamento de Pedido**: Clientes podem cancelar pedidos pendentes, com restauração do estoque.
- **Consulta de Pedidos**: Clientes podem visualizar seus pedidos.
- **Avaliação de Pedido**: Clientes podem avaliar pedidos entregues com nota e comentário.
- **Processamento de Pedidos (Vendedor)**: Vendedores podem acompanhar e processar pedidos, passando por etapas como `PREPARANDO`, `EMBALANDO`, `ENVIANDO`, `EM_TRANSITO`, `ROTA_DE_ENTREGA` e `ENTREGUE`.
- **Relatório de Vendas (Vendedor)**: Vendedores podem visualizar um relatório de vendas, incluindo itens vendidos e resumo de pedidos por status.

## 📦 Estrutura do Projeto

A estrutura do projeto segue o padrão de aplicações Spring Boot:

- `src/main/java/br/edu/infnet/leticia/JSports/`: Contém o código fonte da aplicação.
  - `config/`: Classes de configuração (`SecurityConfig`).
  - `controller/`: Controladores REST para as APIs (ex: `ItemEsportivoController`, `PedidoController`, `UsuarioController`).
  - `dto/`: Objetos de Transferência de Dados (DTOs) para comunicação entre camadas.
  - `enums/`: Enumerações para categorias de itens, formas de pagamento, status de pagamento, status de pedido e tipos de usuário.
  - `model/`: Classes de modelo de domínio e entidades JPA (ex: `Usuario`, `ItemEsportivo`, `Pedido`, `Pagamento`, `Endereco`, `ItemPedido`).
  - `repository/`: Interfaces de repositório para acesso a dados (Spring Data JPA).
  - `service/`: Classes de serviço que contêm a lógica de negócio.
  - `utils/`: Classes utilitárias (ex: `InputUtils`, `LoginUtils`, `MenuUtils` para a interface de console).
  - `view/`: Classes que implementam a interface de console para interação com o usuário (ex: `ClienteView`, `VendedorView`, `MenuInicialView`).
- `src/main/resources/`: Contém arquivos de configuração (ex: `application.properties`).
- `pom.xml`: Arquivo de configuração do Maven, listando dependências e plugins.

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- **Java Development Kit (JDK) 24**.
- **Maven**.

### Passos para Execução

1.  **Clone o Repositório:**

    ```bash
    git clone https://github.com/LeticiaFAAGomes/Leticia_Ferreira_Augusto_Alves_Gomes_PB_TP5--back-end.git
    ```

2.  **Navegue até o Diretório do Projeto:**

    ```bash
    cd Leticia_Ferreira_Augusto_Alves_Gomes_PB_TP5--back-end
    ```

3.  **Compile o Projeto:**

    ```bash
    ./mvnw clean install
    ```

4.  **Execute a Aplicação:**
    Você pode executar a aplicação Spring Boot:

    - **Via IDE (IntelliJ IDEA, Eclipse):**
      Abra o projeto na sua IDE e execute a classe `JSportsApplication.java` como uma aplicação Spring Boot.

## 🗄️ Banco de Dados H2

O projeto utiliza o **H2 Database** como banco de dados em memória para facilitar o desenvolvimento e os testes. O H2 é leve, rápido e não requer instalação de um servidor de banco de dados separado.

### Como Acessar o Console H2

1.  **Inicie a Aplicação:** Certifique-se de que a aplicação Spring Boot esteja em execução.
2.  **Acesse o Console no Navegador:** Abra seu navegador e navegue para o seguinte endereço:

    ```
    http://localhost:8080/h2
    ```

3.  **Credenciais de Conexão:**

    - **Driver Class:** `org.h2.Driver`
    - **JDBC URL:** `jdbc:h2:file:./db/jsportsdb`
    - **User Name:** `sa` (usuário padrão)
    - **Password:** (deixe em branco, senha padrão)

4.  **Conectar:** Clique no botão "Connect". Você será redirecionado para a interface do console H2, onde poderá ver as tabelas criadas pelo JPA e executar queries SQL.

### Acessando a Aplicação

- **Interface de Console:**
  A aplicação iniciará uma interface de console interativa para cadastro, login e operações de cliente/vendedor.

- **API REST (Swagger UI):**
  Uma vez que a aplicação esteja rodando, você pode acessar a documentação da API e testar os endpoints através do Swagger UI no seu navegador:
  ```
  http://localhost:8080/swagger-ui.html
  ```

---

**Desenvolvido por:** Leticia Gomes.
