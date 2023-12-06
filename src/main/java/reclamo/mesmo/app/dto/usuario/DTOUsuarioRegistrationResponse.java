package reclamo.mesmo.app.dto.usuario;

import reclamo.mesmo.app.domain.usuario.Usuario;

public record DTOUsuarioRegistrationResponse(String id, String login) {

    public DTOUsuarioRegistrationResponse(Usuario usuario){
        this(usuario.getId(), usuario.getLogin());
    }

}
