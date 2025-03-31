CREATE TABLE agenda (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  inicio timestamp NOT NULL,
  fim timestamp NOT NULL,
  cliente_id BIGINT NOT NULL,
  CONSTRAINT cliente_fk FOREIGN KEY (cliente_id) REFERENCES CLIENTE(id),
  CONSTRAINT inicio_fim_uk UNIQUE (inicio, fim)
);