package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.dto.reclamacao.*;
import reclamo.mesmo.app.repository.ReclamacaoRepository;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Service
public class ReclamacaoService {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DTOReclamacaoRegistrationResponse register(DTOReclamacaoRegistrationRequest dto) {

        var usuarioReclamante = usuarioRepository.getReferenceById(dto.usuarioReclamanteId());
        var reclamacao = new Reclamacao(dto, usuarioReclamante);
        reclamacaoRepository.save(reclamacao);

        return new DTOReclamacaoRegistrationResponse(reclamacao);
    }

    public DTOReclamacaoAnswerResponse answer(String id, DTOReclamacaoAnswerRequest dto) {
        var reclamacao = reclamacaoRepository.getReferenceById(id);
        var usuarioReclamado = usuarioRepository.getReferenceById(dto.usuarioReclamadoId());

        reclamacao.responder(dto, usuarioReclamado);

        return new DTOReclamacaoAnswerResponse(reclamacao);
    }

    public Page<DTOReclamacaoOpenedList> readAllOpened(Pageable pageable) {
        // TODO: RECUPERAR O NOME DOS USUARIOS
        return reclamacaoRepository.findAllByStatusReclamacao("ABERTA", pageable).map(DTOReclamacaoOpenedList::new);
    }

    public Page<DTOReclamacaoAnsweredList> readAllAnswered(Pageable pageable) {
        //TODO: RECUPERAR O NOME DOS USUARIOS
        return reclamacaoRepository.findAllByStatusReclamacao("FECHADA", pageable).map(DTOReclamacaoAnsweredList::new);
    }

    public Page<DTOReclamacaoClosedList> readAllClosed(Pageable pageable) {
        //TODO: RECUPERAR O NOME DOS USUARIOS
        return reclamacaoRepository.findAllByStatusReclamacao("FECHADA", pageable).map(DTOReclamacaoClosedList::new);
    }

    public void close(String id) {
        var reclamacao = reclamacaoRepository.getReferenceById(id);
        reclamacao.fechar();
    }
}
