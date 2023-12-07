package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.administrador.Administrador;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistrationResponse;
import reclamo.mesmo.app.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioService usuarioService;

    public DTOAdministradorRegistrationResponse register(String nome, String email, String senha){

        var usuario = usuarioService.register(email, senha, true);
        var administrador = new Administrador(nome, usuario);
        administradorRepository.save(administrador);

        return new DTOAdministradorRegistrationResponse(administrador);
    }


}