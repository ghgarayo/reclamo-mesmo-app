package reclamo.mesmo.app.dto.reclamacao;

import jakarta.validation.constraints.NotBlank;

public record DTOReclamacaoResponseRequest(
        @NotBlank
        String id,
        @NotBlank
        String usuarioReclamadoId,
        @NotBlank
        String descricaoResposta

) {
}
