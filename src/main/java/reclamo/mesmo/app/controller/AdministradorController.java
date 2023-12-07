package reclamo.mesmo.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistration;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistrationResponse;
import reclamo.mesmo.app.service.AdministradorService;


@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearer-key")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOAdministradorRegistrationResponse> create(@RequestBody @Valid DTOAdministradorRegistration dto,
                                                                       UriComponentsBuilder uriBuilder) {
        var DTOAdministrador = administradorService.register(dto.nome(), dto.email(), dto.senha());
        var uri = uriBuilder.path("/administrador/{id}").buildAndExpand(DTOAdministrador.id()).toUri();

        return ResponseEntity.created(uri).body(DTOAdministrador);
    }

}
