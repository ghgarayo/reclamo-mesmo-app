package reclamo.mesmo.app.dto.pessoajuridica;

import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public record DTOPessoaJuridicaResponse(String id,
                                        String razaoSocial,
                                        String cnpj,
                                        String telefone,
                                        Endereco endereco)  {


    public DTOPessoaJuridicaResponse(PessoaJuridica pessoaJuridica){
        this(pessoaJuridica.getId(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getTelefone(),
                pessoaJuridica.getEndereco());
    }
}
