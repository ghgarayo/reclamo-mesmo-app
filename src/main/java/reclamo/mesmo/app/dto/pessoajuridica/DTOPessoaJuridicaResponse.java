package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public record DTOPessoaJuridicaResponse(String id,
                                        String razaoSocial,
                                        String cnpj,
                                        String telefone,
                                        String usuarioId,
                                        String email,
                                        Endereco endereco) {


    public DTOPessoaJuridicaResponse(PessoaJuridica pessoaJuridica) {
        this(pessoaJuridica.getId(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getTelefone(),
                pessoaJuridica.getUsuario().getId(),
                pessoaJuridica.getUsuario().getLogin(),
                pessoaJuridica.getEndereco());
    }
}
