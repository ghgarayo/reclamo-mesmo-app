package reclamo.mesmo.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.autenticacao.DTOAuthenticationRequest;
import reclamo.mesmo.app.dto.autenticacao.DTOTokenJwt;
import reclamo.mesmo.app.service.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DTOAuthenticationRequest dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok().body(new DTOTokenJwt(token));

    }

}
