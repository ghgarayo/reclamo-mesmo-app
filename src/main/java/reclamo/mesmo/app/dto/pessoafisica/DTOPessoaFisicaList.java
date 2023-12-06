package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.domain.pessoa.PessoaFisica;

public record DTOPessoaFisicaList(String id, String nome, String cpf, String telefone) {

    public DTOPessoaFisicaList(PessoaFisica pessoaFisica){
        this(pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getCpf(),
                pessoaFisica.getTelefone());
    }
}
