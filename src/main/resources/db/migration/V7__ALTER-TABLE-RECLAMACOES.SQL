ALTER TABLE reclamacoes
ADD COLUMN fechado_por VARCHAR(255),
ADD COLUMN nota_final INTEGER,

ADD CONSTRAINT fk_fechado_por FOREIGN KEY (fechado_por) REFERENCES usuarios(id);
