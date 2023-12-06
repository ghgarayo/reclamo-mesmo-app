package reclamo.mesmo.app.dto.reclamacao;

import jakarta.validation.constraints.NotBlank;

public record DTOReclamacaoAnswerRequest(
        @NotBlank
        String usuarioReclamadoId,
        @NotBlank
        String descricaoResposta

) {
}
