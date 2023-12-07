package reclamo.mesmo.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
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
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRegistration;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdate;
import reclamo.mesmo.app.service.PessoaJuridicaService;

@RestController
@RequestMapping("/api/pessoa-juridica")
@SecurityRequirement(name = "bearer-key")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOPessoaJuridicaResponse> create(@RequestBody @Valid DTOPessoaJuridicaRegistration dto,
                                 UriComponentsBuilder uriBuilder) {

        var DTOPessoaJuridica = service.register(dto.razaoSocial(),
                dto.cnpj(),
                dto.email(),
                dto.senha(),
                dto.telefone(),
                dto.endereco());
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
    @Transactional
    public ResponseEntity<DTOPessoaJuridicaResponse> update(@PathVariable String id,
                                                             @RequestBody @Valid DTOPessoaJuridicaUpdate dto) {
        var DTOPessoaJuridicaUpdated = service.update(id, dto.razaoSocial(), dto.telefone(), dto.endereco());

        return ResponseEntity.ok().body(DTOPessoaJuridicaUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatusCode> delete(@PathVariable String id) {

        service.inactivate(id);

        return ResponseEntity.noContent().build();
    }


}
