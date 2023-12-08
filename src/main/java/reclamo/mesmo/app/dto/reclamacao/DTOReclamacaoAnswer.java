package reclamo.mesmo.app.dto.reclamacao;

import jakarta.validation.constraints.NotBlank;

public record DTOReclamacaoAnswer(
        @NotBlank
        String idReclamacao,
        @NotBlank
        String usuarioReclamadoId,
        @NotBlank
        String descricaoResposta

) {
}
