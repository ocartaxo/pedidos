CREATE TABLE pedidos
(
    id SERIAL PRIMARY KEY NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(256) NOT NULL
);