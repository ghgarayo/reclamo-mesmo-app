package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.dto.endereco.DTOEnderecoRegistrationRequest;

public record DTOPessoaJuridicaUpdateRequest(
        String razaoSocial,
        String cpnj,
        String email,
        String telefone,
        DTOEnderecoRegistrationRequest endereco
) {
}
