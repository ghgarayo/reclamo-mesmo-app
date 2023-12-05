package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.domain.pessoa.PessoaFisica;

public record DTOPessoaFisicaList(String id, String nome, String cpf, String telefone) {
    /*
     * recebe um objeto do tipo pessoa fisica e chama o construtor do record passando os
     * apenas atributos necessários para o retorno de dados para listagem
     */
    public DTOPessoaFisicaList(PessoaFisica pessoaFisica){
        this(pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getCpf(),
                pessoaFisica.getTelefone());
    }
}
