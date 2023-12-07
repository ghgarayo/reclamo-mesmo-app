package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoClose;

public interface ValidadorFechamentoReclamacao {
    void validar(Reclamacao reclamacao, Usuario admin);
}
