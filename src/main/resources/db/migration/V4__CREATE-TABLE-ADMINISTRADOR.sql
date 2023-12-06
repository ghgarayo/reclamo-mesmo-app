CREATE TABLE administrador (
                               id VARCHAR(255) PRIMARY KEY,
                               nome VARCHAR(255) NOT NULL,
                               is_active BOOLEAN DEFAULT TRUE,
                               usuario_id VARCHAR(255) NOT NULL,

                               CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);