package reclamo.mesmo.app.domain.reclamacao.validacaocriacao;

import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoRegistration;

public interface ValidadorCriacaoReclamacao {

    void validar(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao);
}
