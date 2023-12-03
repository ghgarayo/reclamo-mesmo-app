package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;

public record DTOPessoaFisicaResponse(String id,
                                      String nome,
                                      String cpf,
                                      String telefone,
                                      String email,
                                      Endereco endereco) {

    public DTOPessoaFisicaResponse(PessoaFisica pessoaFisica) {
        this(pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getCpf(),
                pessoaFisica.getTelefone(),
                pessoaFisica.getEmail(),
                pessoaFisica.getEndereco());
    }


}
