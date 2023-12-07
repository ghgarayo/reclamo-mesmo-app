package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

import java.time.LocalDateTime;

public record DTOReclamacaoAnsweredList(String id,
                                        String usuarioReclamante,
                                        String descricaoReclamacao,
                                        LocalDateTime dataReclamacao,
                                        String usuarioReclamado,
                                        String descricaoResposta,
                                        LocalDateTime dataResposta) {

    public DTOReclamacaoAnsweredList(Reclamacao reclamacao){
        this(reclamacao.getId(),
                reclamacao.getUsuarioReclamante().getLogin(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDataReclamacao(),
                reclamacao.getUsuarioReclamado().getLogin(),
                reclamacao.getDescricaoResposta(),
                reclamacao.getDataResposta());
    }

}
