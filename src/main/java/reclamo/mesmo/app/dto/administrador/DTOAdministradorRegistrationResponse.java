package reclamo.mesmo.app.dto.administrador;

import reclamo.mesmo.app.domain.administrador.Administrador;

public record DTOAdministradorRegistrationResponse(
        String id,
        String nome
) {

    public DTOAdministradorRegistrationResponse(Administrador administrador) {
        this(administrador.getId(), administrador.getNome());
    }

}