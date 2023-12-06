package reclamo.mesmo.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRegistrationRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdateRequest;
import reclamo.mesmo.app.service.PessoaFisicaService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping
    public ResponseEntity<DTOPessoaFisicaResponse> create(@RequestBody @Valid DTOPessoaFisicaRegistrationRequest dto,
            UriComponentsBuilder uriBuilder) {
        var DTOPessoaFisica = pessoaFisicaService.register(dto);
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
    public ResponseEntity<DTOPessoaFisicaResponse> update(@PathVariable String id,
            @RequestBody @Valid DTOPessoaFisicaUpdateRequest dto) {
        var DTOPessoaFisicaUpdated = pessoaFisicaService.update(id, dto);

        return ResponseEntity.ok().body(DTOPessoaFisicaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        pessoaFisicaService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

}
