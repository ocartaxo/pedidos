CREATE TABLE itens_do_pedido(
    id SERIAL PRIMARY KEY NOT NULL,
    descricao VARCHAR(256) DEFAULT NULL,
    quantidade INTEGER NOT NULL,
    pedido_id BIGINT NOT NULL,

    FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);