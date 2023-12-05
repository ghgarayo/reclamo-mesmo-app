package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public record DTOPessoaJuridicaList(String id, String razaoSocial, String cnpj, String telefone) {
    /*
     * recebe um objeto do tipo pessoa juridica e chama o construtor do record passando os
     * apenas atributos necessários para o retorno de dados para listagem
     */
    public DTOPessoaJuridicaList(PessoaJuridica pessoaJuridica){
        this(pessoaJuridica.getId(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getTelefone());
    }
}
