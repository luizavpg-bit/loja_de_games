<table>
<tr>

<td width="35%" align="center">

<img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original-wordmark.svg" width="180"/>

</td>

<td width="65%">

# ✨ Loja de Games: API REST com Security & JWT

<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">

Este repositório contém a resolução da atividade prática avaliada sobre Spring Boot REST API, CRUD com relacionamentos e autenticação via JWT, proposta pela Generation Brasil.

</td>

</tr>
</table>

---

## 🌸 Habilidades trabalhadas

- Construção de API RESTful com **Spring Boot**
- Mapeamento Objeto-Relacional com **Spring Data JPA** e **Hibernate**
- Configuração de Segurança e Autenticação com **Spring Security** e **JWT (JSON Web Token)**
- Tratamento e Criptografia de Senhas (**BCryptPasswordEncoder**)
- Validação de Dados de Entrada (**Bean Validation / Jakarta Validation**)
- Mapeamento de Relacionamentos N:1, 1:N e N:N (**@ManyToOne, @OneToMany**)
- Validação de Regras de Negócio customizadas (Cadastro liberado apenas para **>= 18 anos**)

---

## 🌸 Estrutura do projeto
---

## 🌸 Endpoints da API

### 🔑 Autenticação (`/auth`)
- `POST /auth/register` — Cadastro de usuário (Validação de idade mínima: 18 anos)
- `POST /auth/login` — Autenticação de usuário e geração de Token JWT

###  Categorias (`/categorias`)
- `GET /categorias` — Listar todas as categorias
- `GET /categorias/{id}` — Buscar categoria por ID
- `GET /categorias/tipo/{tipo}` — Buscar categorias por tipo
- `POST /categorias` — Criar nova categoria
- `PUT /categorias` — Atualizar categoria existente
- `DELETE /categorias/{id}` — Deletar categoria por ID

### 🎮 Produtos (`/produtos`)
- `GET /produtos` — Listar todos os produtos
- `GET /produtos/{id}` — Buscar produto por ID
- `GET /produtos/nome/{nome}` — Buscar produtos por nome
- `GET /produtos/preco_maior/{preco}` — Filtrar por preço maior (Ordem crescente)
- `GET /produtos/preco_menor/{preco}` — Filtrar por preço menor (Ordem decrescente)
- `POST /produtos` — Criar novo produto vinculado a uma categoria
- `PUT /produtos` — Atualizar produto existente
- `DELETE /produtos/{id}` — Deletar produto por ID

### 🛒 Pedidos (`/pedidos`) *(Rota Protegida)*
- `GET /pedidos` — Listar todos os pedidos
- `GET /pedidos/{id}` — Buscar pedido por ID
- `POST /pedidos` — Realizar novo pedido
- `DELETE /pedidos/{id}` — Cancelar/Deletar pedido
---
## 🌸 Regra de Negócio: Cadastro +18

No endpoint `POST /auth/register`, é efetuada a verificação da idade informada no JSON. Caso o usuário tenha menos de 18 anos, a requisição é recusada com status **400 Bad Request**:

```json
{
  "message": "Cadastro permitido apenas para maiores de 18 anos"
}
```

---

## 🛢️ Configuração do Banco de Dados

1. Certifique-se de ter o **MySQL** instalado e rodando.
2. Duplique o arquivo `.env.example` na raiz do projeto e renomeie a cópia para `.env`.
3. Preencha com o seu usuário e senha do MySQL local no arquivo `.env`:

```env
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

O Spring criará a base `db_lojagames` automaticamente ao iniciar. Caso deseje popular o banco com dados de teste, execute o arquivo `script/script_lojagames.sql` no MySQL Workbench.

---

## 🌸 Como executar

1. **Clone o repositório:**

```bash
git clone https://github.com/SEU_USUARIO/lojagames.git
```

2. **Configure o banco de dados** seguindo os passos da seção [Configuração do Banco de Dados](#-configuração-do-banco-de-dados) acima.

3. **Execute o projeto:**
   Na raiz do projeto, rode o comando:

```bash
mvn spring-boot:run
```

4. **Acesse a API:**
   A aplicação estará rodando em `http://localhost:8080`.

---

## 🌸 Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Security & JWT
- Spring Data JPA
- MySQL
- Maven

---

## 👤 Feito por:
**Luiza Valentina Paolinelli Guimarães**
