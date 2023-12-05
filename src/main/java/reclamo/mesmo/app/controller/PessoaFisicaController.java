package reclamo.mesmo.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdateRequest;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegisterRequest;
import reclamo.mesmo.app.service.PessoaFisicaService;
import reclamo.mesmo.app.service.UsuarioService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DTOPessoaFisicaResponse> create(@RequestBody @Valid DTOPessoaFisicaRequest dto,
            UriComponentsBuilder uriBuilder) {
        var DTOpessoaFisica = pessoaFisicaService.register(dto);
        var uri = uriBuilder.path("/pessoa-fisica/{id}").buildAndExpand(DTOpessoaFisica.id()).toUri();

        return ResponseEntity.created(uri).body(DTOpessoaFisica);
    }

    @GetMapping
    public ResponseEntity<Page<DTOPessoaFisicaList>> readAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var DTOpessoaFisicaList = pessoaFisicaService.readAll(pageable);

        return ResponseEntity.ok().body(DTOpessoaFisicaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPessoaFisicaResponse> readById(@PathVariable String id) {
        var DTOPessoaFisicaDetailed = pessoaFisicaService.readById(id);

        return ResponseEntity.ok().body(DTOPessoaFisicaDetailed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOPessoaFisicaResponse> update(@PathVariable String id,
            @RequestBody @Valid DTOPessoaFisicaUpdateRequest dto) {
        var DTOpessoaFisicaUpdated = pessoaFisicaService.update(id, dto);

        return ResponseEntity.ok().body(DTOpessoaFisicaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatusCode> delete(@PathVariable String id) {
        pessoaFisicaService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

}
