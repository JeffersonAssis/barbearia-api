CREATE TABLE cliente (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  telefone VARCHAR(11) NOT NULL,
  CONSTRAINT email_uk UNIQUE (email),
  CONSTRAINT telefone_uk UNIQUE (telefone)
);