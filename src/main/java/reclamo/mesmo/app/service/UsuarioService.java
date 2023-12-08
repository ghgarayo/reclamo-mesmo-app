package reclamo.mesmo.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegistration;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario register(DTOUsuarioRegistration dto){
        return register(dto.email(), dto.senha(), dto.isAdmin());

    }

    public Usuario register(String email, String senha, boolean isAdmin){
        var hashedPassword = encoder.encode(senha);
        var usuario = new Usuario(email, hashedPassword, isAdmin);
        usuarioRepository.save(usuario);

        return usuario;
    }
}
