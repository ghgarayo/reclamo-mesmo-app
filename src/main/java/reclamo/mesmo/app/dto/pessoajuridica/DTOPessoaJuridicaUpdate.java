package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.dto.endereco.DTOEndereco;

public record DTOPessoaJuridicaUpdate(
        String razaoSocial,
        String cpnj,
        String email,
        String telefone,
        DTOEndereco endereco
) {
}
