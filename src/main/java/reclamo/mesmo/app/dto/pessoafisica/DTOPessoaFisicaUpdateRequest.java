package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.dto.endereco.DTOEnderecoRegisterRequest;

public record DTOPessoaFisicaUpdateRequest(
        String nome,
        String telefone,
        String email,
        DTOEnderecoRegisterRequest endereco) {
}
