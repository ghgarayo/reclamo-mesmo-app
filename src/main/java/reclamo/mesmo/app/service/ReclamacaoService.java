package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.reclamacao.validacaocriacao.ValidadorCriacaoReclamacao;
import reclamo.mesmo.app.domain.reclamacao.validacaofechamento.ValidadorFechamentoReclamacao;
import reclamo.mesmo.app.domain.reclamacao.validacaoresposta.ValidadorRespostaReclamacao;
import reclamo.mesmo.app.dto.reclamacao.*;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;
import reclamo.mesmo.app.repository.UsuarioRepository;

import java.util.List;

@Service
public class ReclamacaoService {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorCriacaoReclamacao> validadoresDeCriacaoDeReclamacao;
    @Autowired
    private List<ValidadorRespostaReclamacao> validadoresDeRespostaDeReclamacao;
    @Autowired
    private List<ValidadorFechamentoReclamacao> validadoresDeFechamentoDeReclamacao;

    public DTOReclamacaoRegistrationResponse register(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao) {

        validadoresDeCriacaoDeReclamacao.forEach(validador -> validador.validar(usuarioReclamanteId, cpfCnpjReclamado, descricaoReclamacao));

        var usuarioReclamante = usuarioRepository.getReferenceById(usuarioReclamanteId);
        var reclamacao = new Reclamacao(usuarioReclamante, cpfCnpjReclamado, descricaoReclamacao);
        reclamacaoRepository.save(reclamacao);

        return new DTOReclamacaoRegistrationResponse(reclamacao);
    }

    public DTOReclamacaoAnswerResponse answer(DTOReclamacaoAnswerRequest dto) {

        if (!reclamacaoRepository.existsById(dto.idReclamacao())) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        var reclamacao = reclamacaoRepository.getReferenceById(dto.idReclamacao());
        var usuarioReclamado = usuarioRepository.getReferenceById(dto.usuarioReclamadoId());

        validadoresDeRespostaDeReclamacao.forEach(validador -> validador.validar(dto));

        reclamacao.responder(dto, usuarioReclamado);
        reclamacaoRepository.save(reclamacao);

        return new DTOReclamacaoAnswerResponse(reclamacao);
    }

    public Page<DTOReclamacaoOpenedList> readAllOpened(Pageable pageable) {
        return reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.ABERTA, pageable).map(DTOReclamacaoOpenedList::new);
    }

    public Page<DTOReclamacaoAnsweredList> readAllAnswered(Pageable pageable) {
        return reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.RESPONDIDA, pageable).map(DTOReclamacaoAnsweredList::new);
    }

    public Page<DTOReclamacaoClosedList> readAllClosed(Pageable pageable) {
        return reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.FECHADA, pageable).map(DTOReclamacaoClosedList::new);
    }

    public void close(String idReclamacao, String usuarioId) {

        var administrador = usuarioRepository.findByUsuarioIdAndIsAdmin(usuarioId, true);
        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);

        if(administrador == null){
            throw new ValidacaoException("Usuário não encontrado");
        }
        validadoresDeFechamentoDeReclamacao.forEach(validador -> validador.validar(reclamacao, administrador));

        reclamacao.fechar(administrador);
        reclamacaoRepository.save(reclamacao);
    }

    public void grade(String idReclamacao, Integer notaFinal) {
        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);
        reclamacao.avaliar(idReclamacao, notaFinal);
        reclamacaoRepository.save(reclamacao);
    }

}
