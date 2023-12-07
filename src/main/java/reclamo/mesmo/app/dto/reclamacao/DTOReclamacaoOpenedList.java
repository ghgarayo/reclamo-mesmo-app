package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

import java.time.LocalDateTime;

public record DTOReclamacaoOpenedList(String id,
                                      String usuarioReclamante,
                                      String descricaoReclamacao,
                                      LocalDateTime dataReclamacao) {

    public DTOReclamacaoOpenedList(Reclamacao reclamacao) {
            this(reclamacao.getId(),
                reclamacao.getUsuarioReclamante().getLogin(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDataReclamacao());
    }
}
