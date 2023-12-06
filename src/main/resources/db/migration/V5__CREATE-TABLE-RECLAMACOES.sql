CREATE TABLE reclamacoes (
                             id VARCHAR(255) PRIMARY KEY,
                             reclamante_usuario_id VARCHAR(255) NOT NULL,
                             cpf_cnpj_reclamado VARCHAR(255) NOT NULL,
                             reclamado_usuario_id VARCHAR(255),
                             descricao_reclamacao TEXT NOT NULL,
                             data_reclamacao TIMESTAMP NOT NULL,
                             descricao_resposta TEXT,
                             data_resposta TIMESTAMP,
                             status_reclamacao VARCHAR(255) NOT NULL,
                             FOREIGN KEY (reclamante_usuario_id) REFERENCES usuarios(id),
                             FOREIGN KEY (reclamado_usuario_id) REFERENCES usuarios(id)
);