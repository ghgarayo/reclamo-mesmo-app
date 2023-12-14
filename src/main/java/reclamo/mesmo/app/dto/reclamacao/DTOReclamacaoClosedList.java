package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DTOReclamacaoClosedList(String id,
                                     String usuarioReclamante,
                                     String descricaoReclamacao,
                                     LocalDateTime dataReclamacao,
                                     String usuarioReclamado,
                                     String descricaoResposta,
                                     LocalDateTime dataResposta,
                                      Integer notaFinal
                                    ){

    public DTOReclamacaoClosedList(Reclamacao reclamacao){
        this(reclamacao.getId(),
                reclamacao.getUsuarioReclamante().getId(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDataReclamacao(),
                reclamacao.getUsuarioReclamado().getId(),
                reclamacao.getDescricaoResposta(),
                reclamacao.getDataResposta(),
                reclamacao.getNotaFinal());
    }
}
