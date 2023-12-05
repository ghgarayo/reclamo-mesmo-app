package reclamo.mesmo.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegisterRequest;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario register(DTOUsuarioRegisterRequest dto) {
        var hashedPassword = encoder.encode(dto.senha());
        var usuario = new Usuario(dto.email(), hashedPassword);
        usuarioRepository.save(usuario);

        return usuario;
    }
}
