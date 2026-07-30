-- Criação do banco de dados (caso não use a opção automática do spring no Java)
CREATE DATABASE IF NOT EXISTS db_lojagames;
USE db_lojagames;

-- Dados para teste inicial da tabela de categorias
INSERT INTO tb_categorias (tipo) VALUES ('RPG');
INSERT INTO tb_categorias (tipo) VALUES ('Ação e Aventura');
INSERT INTO tb_categorias (tipo) VALUES ('Estratégia');

-- Dados para teste inicial da tabela de produtos (categoria_id 1 = RPG, 2 = Ação)
INSERT INTO tb_produtos (nome, preco, foto, categoria_id) 
VALUES ('The Witcher 3', 129.90, 'https://i.imgur.com/example.jpg', 1);

INSERT INTO tb_produtos (nome, preco, foto, categoria_id) 
VALUES ('God of War Ragnarök', 299.90, 'https://i.imgur.com/example2.jpg', 2);
