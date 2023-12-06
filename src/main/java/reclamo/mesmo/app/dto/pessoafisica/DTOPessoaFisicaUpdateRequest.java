package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.dto.endereco.DTOEnderecoRegistrationRequest;

public record DTOPessoaFisicaUpdateRequest(
        String nome,
        String telefone,
        String email,
        DTOEnderecoRegistrationRequest endereco) {
}
