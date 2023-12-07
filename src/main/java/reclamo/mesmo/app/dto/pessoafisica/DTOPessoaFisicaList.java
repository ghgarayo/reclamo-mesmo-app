package reclamo.mesmo.app.dto.pessoafisica;

import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.domain.usuario.Usuario;

public record DTOPessoaFisicaList(String id, String nome, String cpf, String telefone, String usuarioId, String usuarioEmail) {

    public DTOPessoaFisicaList(PessoaFisica pessoaFisica) {
        this(pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getCpf(),
                pessoaFisica.getTelefone(),
                pessoaFisica.getUsuario().getId(),
                pessoaFisica.getUsuario().getLogin());
    }
}
