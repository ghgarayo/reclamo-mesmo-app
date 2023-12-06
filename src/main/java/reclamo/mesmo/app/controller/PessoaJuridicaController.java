package reclamo.mesmo.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaList;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRegistrationRequest;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdateRequest;
import reclamo.mesmo.app.service.PessoaJuridicaService;

@RestController
@RequestMapping("/api/pessoa-juridica")
@SecurityRequirement(name = "bearer-key")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService service;

    @PostMapping
    public ResponseEntity<DTOPessoaJuridicaResponse> create(@RequestBody @Valid DTOPessoaJuridicaRegistrationRequest dto,
                                 UriComponentsBuilder uriBuilder) {

        var DTOPessoaJuridica = service.register(dto);
        var uri = uriBuilder.path("/pessoa-juridica/{id}").buildAndExpand(DTOPessoaJuridica.id()).toUri();

        return ResponseEntity.created(uri).body(DTOPessoaJuridica);
    }

    @GetMapping
    public ResponseEntity<Page<DTOPessoaJuridicaList>> readAll(
            @PageableDefault(size = 10, sort = "razaoSocial") Pageable pageable) {
        var DTOPessoaJuridicaList = service.readAll(pageable);

        return ResponseEntity.ok().body(DTOPessoaJuridicaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPessoaJuridicaResponse> readById(@PathVariable String id) {
        var DTOPessoaJuridicaDetailed = service.readById(id);

          return ResponseEntity.ok().body(DTOPessoaJuridicaDetailed);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOPessoaJuridicaResponse> update(@PathVariable String id,
                                                             @RequestBody @Valid DTOPessoaJuridicaUpdateRequest dto) {
        var DTOPessoaJuridicaUpdated = service.update(id, dto);

        return ResponseEntity.ok().body(DTOPessoaJuridicaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatusCode> delete(@PathVariable String id) {
        service.inactivate(id);

        return ResponseEntity.noContent().build();
    }


}
