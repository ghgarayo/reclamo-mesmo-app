package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.dto.endereco.DTOEndereco;

public record DTOPessoaFisicaUpdate(
        String nome,
        String telefone,
        DTOEndereco endereco) {
}
