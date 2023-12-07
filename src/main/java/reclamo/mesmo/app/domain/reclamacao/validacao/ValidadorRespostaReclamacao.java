package reclamo.mesmo.app.domain.reclamacao.validacao;

import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoAnswerRequest;

public interface ValidadorRespostaReclamacao {
    void validar(DTOReclamacaoAnswerRequest dto);
}
