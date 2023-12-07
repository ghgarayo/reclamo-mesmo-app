package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public record DTOPessoaJuridicaList(String id, String razaoSocial, String cnpj, String telefone, String usuarioId, String usuarioEmail) {

    public DTOPessoaJuridicaList(PessoaJuridica pessoaJuridica){
        this(pessoaJuridica.getId(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getTelefone(),
                pessoaJuridica.getUsuario().getId(),
                pessoaJuridica.getUsuario().getLogin());
    }
}
