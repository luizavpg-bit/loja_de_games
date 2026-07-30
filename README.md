# Loja de Games - CRUD com Relacionamento Many-to-One e JWT

Atividade prática Dev. Full Stack Jr. (Generation Brasil).

## Estrutura

```
com.generation.lojagames
├── model         → Categoria, Produto, Usuario, UsuarioLogin, Pedido
├── repository    → JpaRepository de cada entidade
├── service       → regras de negócio
├── controller    → endpoints REST
└── security      → JWTUtil, JWTAuthorizationFilter, UserDetailsServiceImpl, SecurityConfig
```

## Configuração do banco

Edite `src/main/resources/application.properties`:

```
spring.datasource.username=root
spring.datasource.password=sua_senha
```

## Rodando

```
mvn spring-boot:run
```

## Endpoints

- `POST /auth/register` — cadastro (idade >= 18)
- `POST /auth/login` — login, retorna token JWT
- `GET/POST/PUT/DELETE /categorias`
- `GET/POST/PUT/DELETE /produtos`
- `GET/POST/DELETE /pedidos` — **protegido**, exige header `Authorization: Bearer <token>`

### Exemplo: cadastro recusado por idade

```json
POST /auth/register
{ "nome": "João", "email": "joao@email.com", "senha": "123456", "idade": 16 }
```

Resposta (400):
```json
{ "message": "Cadastro permitido apenas para maiores de 18 anos" }
```
