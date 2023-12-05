ALTER TABLE pessoa_fisica
DROP COLUMN senha,
DROP COLUMN email,
ADD COLUMN usuario_id VARCHAR(255) NOT NULL,
ADD CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id);

ALTER TABLE pessoa_juridica
DROP COLUMN senha,
DROP COLUMN email,
ADD COLUMN usuario_id VARCHAR(255) NOT NULL,
ADD CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
