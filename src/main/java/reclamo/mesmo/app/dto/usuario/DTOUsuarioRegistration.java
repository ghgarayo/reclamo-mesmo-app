package reclamo.mesmo.app.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOUsuarioRegistration(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        Boolean isAdmin
) {

}
