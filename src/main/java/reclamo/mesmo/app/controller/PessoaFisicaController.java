package reclamo.mesmo.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdateRequest;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;
import reclamo.mesmo.app.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoa-fisica")
public class PessoaFisicaController {
    @Autowired
    private PessoaFisicaService service;

    @Autowired
    private PessoaFisicaRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DTOPessoaFisicaRequest dto,
                                 UriComponentsBuilder uriBuilder) {
        var DTOpessoaFisica = service.register(dto);
        var uri = uriBuilder.path("/pessoa-fisica/{id}").buildAndExpand(DTOpessoaFisica.id()).toUri();

        return ResponseEntity.created(uri).body(DTOpessoaFisica);
    }

    @GetMapping
    public ResponseEntity<Page<DTOPessoaFisicaList>> readAll(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(service.readAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPessoaFisicaResponse> readById(@PathVariable String id) {
        return ResponseEntity.ok(service.readById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOPessoaFisicaResponse> update(@PathVariable String id,
                                                          @RequestBody @Valid DTOPessoaFisicaUpdateRequest request) {
        var pessoaFisica = repository.findById(id).orElse(null);
        if (pessoaFisica == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaFisica.update(request);
        repository.save(pessoaFisica);

        return ResponseEntity.ok(new DTOPessoaFisicaResponse(pessoaFisica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        var pessoaFisica = repository.findById(id).orElse(null);
        if (pessoaFisica == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaFisica.inativar();
        repository.save(pessoaFisica);

        return ResponseEntity.noContent().build();
    }


}
