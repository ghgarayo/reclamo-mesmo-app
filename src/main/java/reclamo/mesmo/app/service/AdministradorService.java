package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.administrador.Administrador;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistrationRequest;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistrationResponse;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegistrationRequest;
import reclamo.mesmo.app.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioService usuarioService;

    public DTOAdministradorRegistrationResponse register(DTOAdministradorRegistrationRequest dto){
        var usuarioDTO = new DTOUsuarioRegistrationRequest(dto.email(), dto.senha());
        var usuario = usuarioService.register(usuarioDTO);
        var administrador = new Administrador(dto, usuario);
        administradorRepository.save(administrador);

        return new DTOAdministradorRegistrationResponse(administrador);
    }


}