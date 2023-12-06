package reclamo.mesmo.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reclamo.mesmo.app.dto.reclamacao.*;
import reclamo.mesmo.app.service.ReclamacaoService;

@RestController
@RequestMapping("/api/reclamacao")
@SecurityRequirement(name = "bearer-key")
public class ReclamacaoController {

    @Autowired
    private ReclamacaoService reclamacaoService;

    @PostMapping
    public ResponseEntity<DTOReclamacaoRegistrationResponse> create(@RequestBody @Valid DTOReclamacaoRegistrationRequest dto,
                                                                    UriComponentsBuilder uriBuilder) {
        System.out.println(dto);
        var DTOReclamacao = reclamacaoService.register(dto);
        var uri = uriBuilder.path("/reclamacao/{id}").buildAndExpand(DTOReclamacao.id()).toUri();

        return ResponseEntity.created(uri).body(DTOReclamacao);
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity<DTOReclamacaoAnswerResponse> answer(@PathVariable String id, @RequestBody @Valid DTOReclamacaoAnswerRequest dto,
                                                              UriComponentsBuilder uriBuilder) {
        var DTOReclamacao = reclamacaoService.answer(id, dto);
        var uri = uriBuilder.path("/reclamacao/{id}").buildAndExpand(DTOReclamacao.id()).toUri();

        return ResponseEntity.created(uri).body(DTOReclamacao);
    }

    @GetMapping("/list/opened")
    public ResponseEntity<Page<DTOReclamacaoOpenedList>> readAllOpened(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var DTOReclamacaoList = reclamacaoService.readAllOpened(pageable);

        return ResponseEntity.ok().body(DTOReclamacaoList);
    }

    @GetMapping("/list/answered")
    public ResponseEntity<Page<DTOReclamacaoAnsweredList>> readAllAnswered(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var DTOReclamacaoList = reclamacaoService.readAllAnswered(pageable);

        return ResponseEntity.ok().body(DTOReclamacaoList);
    }

    @GetMapping("/list/closed")
    public ResponseEntity<Page<DTOReclamacaoClosedList>> readAllClosed(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var DTOReclamacaoList = reclamacaoService.readAllClosed(pageable);

        return ResponseEntity.ok().body(DTOReclamacaoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> close(@PathVariable String id) {
        reclamacaoService.close(id);

        return ResponseEntity.noContent().build();
    }

}
