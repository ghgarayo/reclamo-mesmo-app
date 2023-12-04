package reclamo.mesmo.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaList;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRequest;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdateRequest;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;

@RestController
@RequestMapping("/pessoa-juridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DTOPessoaJuridicaRequest request,
                                 UriComponentsBuilder uriBuilder) {

        var pessoaJuridica = new PessoaJuridica(request);
        repository.save(pessoaJuridica);
        var uri = uriBuilder.path("/pessoa-juridica/{id}").buildAndExpand(pessoaJuridica.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOPessoaJuridicaResponse(pessoaJuridica));
    }

    @GetMapping
    public ResponseEntity<Page<DTOPessoaJuridicaList>> readAll(
            @PageableDefault(size = 10, sort = "razaoSocial") Pageable pageable) {

        var page = repository.findAllByIsActiveTrue(pageable).map(DTOPessoaJuridicaList::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPessoaJuridicaResponse> readById(@PathVariable String id) {
        var pessoaJuridica = repository.findById(id).orElse(null);
          return ResponseEntity.ok(new DTOPessoaJuridicaResponse(pessoaJuridica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOPessoaJuridicaResponse> update(@PathVariable String id,
                                                             @RequestBody @Valid DTOPessoaJuridicaUpdateRequest request) {
        var pessoaJuridica = repository.findById(id).orElse(null);
        if (pessoaJuridica == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaJuridica.update(request);
        repository.save(pessoaJuridica);

        return ResponseEntity.ok(new DTOPessoaJuridicaResponse(pessoaJuridica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        var pessoaJuridica = repository.findById(id).orElse(null);
        if (pessoaJuridica == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaJuridica.inativar();
        repository.save(pessoaJuridica);

        return ResponseEntity.noContent().build();
    }


}
