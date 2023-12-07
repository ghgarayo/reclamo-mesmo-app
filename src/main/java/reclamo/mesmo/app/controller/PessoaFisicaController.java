package reclamo.mesmo.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRegistration;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdate;
import reclamo.mesmo.app.service.PessoaFisicaService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/pessoa-fisica")
@SecurityRequirement(name = "bearer-key")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOPessoaFisicaResponse> create(@RequestBody @Valid DTOPessoaFisicaRegistration dto,
                                                          UriComponentsBuilder uriBuilder) {
        var DTOPessoaFisica = pessoaFisicaService.register(dto.nome(),
                dto.cpf(),
                dto.email(),
                dto.senha(),
                dto.telefone(),
                dto.endereco());

        var uri = uriBuilder.path("/pessoa-fisica/{id}").buildAndExpand(DTOPessoaFisica.id()).toUri();

        return ResponseEntity.created(uri).body(DTOPessoaFisica);
    }

    @GetMapping
    public ResponseEntity<Page<DTOPessoaFisicaList>> readAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var DTOPessoaFisicaList = pessoaFisicaService.readAll(pageable);

        return ResponseEntity.ok().body(DTOPessoaFisicaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPessoaFisicaResponse> readById(@PathVariable String id) {
        var DTOPessoaFisicaDetailed = pessoaFisicaService.readById(id);

        return ResponseEntity.ok().body(DTOPessoaFisicaDetailed);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DTOPessoaFisicaResponse> update(@PathVariable String id,
                                                          @RequestBody @Valid DTOPessoaFisicaUpdate dto) {

        var DTOPessoaFisicaUpdated = pessoaFisicaService.update(id,
                dto.nome(),
                dto.telefone(),
                dto.endereco());

        return ResponseEntity.ok().body(DTOPessoaFisicaUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) {
        pessoaFisicaService.inactivate(id);
        return ResponseEntity.noContent().build();
    }

}
