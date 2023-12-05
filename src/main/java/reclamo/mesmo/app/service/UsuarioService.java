package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegisterRequest;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario register(DTOUsuarioRegisterRequest dto){
        var usuario = new Usuario(dto);
        usuarioRepository.save(usuario);

        return usuario;
    }
}
