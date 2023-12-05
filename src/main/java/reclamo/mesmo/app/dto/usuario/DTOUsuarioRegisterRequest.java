package reclamo.mesmo.app.dto.usuario;

import reclamo.mesmo.app.domain.usuario.Usuario;

public record DTOUsuarioRegisterRequest(
        String email,
        String senha
) {
    public static record DTOUsuarioResponse(String id, String login) {

        public DTOUsuarioResponse(Usuario usuario){
            this(usuario.getId(), usuario.getLogin());
        }

    }
}
