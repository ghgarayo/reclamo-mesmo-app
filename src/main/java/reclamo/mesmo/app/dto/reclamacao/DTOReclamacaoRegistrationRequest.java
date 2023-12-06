package reclamo.mesmo.app.dto.reclamacao;

import jakarta.validation.constraints.NotBlank;

public record DTOReclamacaoRegistrationRequest(
        @NotBlank
        String usuarioReclamanteId,
        @NotBlank
        String cpfCnpjReclamado,
        @NotBlank
        String descricaoReclamacao
) {
}
