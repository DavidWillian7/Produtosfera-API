INSERT INTO categoria (nome, descricao) VALUES
    ('Eletrônicos', 'Produtos eletrônicos em geral'),
    ('Roupas', 'Roupas de diferentes estilos'),
    ('Alimentos', 'Diversos tipos de alimentos'),
    ('Livros', 'Livros de diferentes gêneros'),
    ('Esportes', 'Artigos esportivos para diversas modalidades');

INSERT INTO produto (codigo, nome, descricao, preco, quantidade_estoque) VALUES
    ('PROD001', 'Smartphone', 'Smartphone de última geração', 1500.00, 100),
    ('PROD002', 'Camiseta', 'Camiseta básica de algodão', 25.00, 200),
    ('PROD003', 'Arroz', 'Arroz branco tipo 1', 10.00, 500),
    ('PROD004', 'Dom Casmurro', 'Romance clássico de Machado de Assis', 30.00, 50),
    ('PROD005', 'Bola de Futebol', 'Bola oficial de futebol', 50.00, 30);

INSERT INTO produto_categoria (id_produto, id_categoria) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);