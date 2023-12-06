package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

public record DTOReclamacaoAnswerResponse(
        String id,
        String usuarioReclamanteId,
        String usuarioReclamadoId,
        String descricao,
        String descricaoResposta,
        String dataHoraResposta,
        String dataHoraReclamacao,
        String status
) {

    public DTOReclamacaoAnswerResponse(Reclamacao reclamacao){
        this(
                reclamacao.getId(),
                reclamacao.getUsuarioReclamante().getId(),
                reclamacao.getUsuarioReclamado().getId(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDescricaoResposta(),
                reclamacao.getDataResposta().toString(),
                reclamacao.getDataReclamacao().toString(),
                reclamacao.getStatusReclamacao().toString()
        );
    }
}
