package reclamo.mesmo.app.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DTOUsuarioRegistrationRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha
) {

}
