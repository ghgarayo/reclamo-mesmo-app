package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DTOReclamacaoOpenedList(String id,
                                      Usuario usuarioReclamante,
                                      String descricaoReclamacao,
                                      LocalDateTime dataReclamacao,
                                      Usuario usuarioReclamado,
                                      String descricaoResposta,
                                      LocalDateTime dataResposta){

    public DTOReclamacaoOpenedList(Reclamacao reclamacao){
        this(reclamacao.getId(),
                reclamacao.getUsuarioReclamante(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDataReclamacao(),
                reclamacao.getUsuarioReclamado(),
                reclamacao.getDescricaoResposta(),
                reclamacao.getDataResposta());
    }
}
