CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    preco NUMERIC(10, 2) NOT NULL,
    quantidade_estoque INTEGER NOT NULL
);

CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE produto_categoria (
    id_produto INTEGER REFERENCES produto(id),
    id_categoria INTEGER REFERENCES categoria(id),
    PRIMARY KEY (id_produto, id_categoria)
);