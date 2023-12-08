package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.reclamacao.validacaoavaliacao.ValidadorNotaFinalReclamacao;
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
    @Autowired
    private List<ValidadorNotaFinalReclamacao> validadoresDeNotaFinalReclamacao;

    public DTOReclamacaoRegistrationResponse register(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao) {

        validadoresDeCriacaoDeReclamacao.forEach(validador -> validador.validar(usuarioReclamanteId, cpfCnpjReclamado, descricaoReclamacao));

        var usuarioReclamante = usuarioRepository.getReferenceById(usuarioReclamanteId);
        var reclamacao = new Reclamacao(usuarioReclamante, cpfCnpjReclamado, descricaoReclamacao);
        reclamacaoRepository.save(reclamacao);

        return new DTOReclamacaoRegistrationResponse(reclamacao);
    }

    public DTOReclamacaoAnswerResponse answer(String idReclamacao, String usuarioReclamadoId, String descricaoResposta) {

        if (!reclamacaoRepository.existsById(idReclamacao)) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        validadoresDeRespostaDeReclamacao.forEach(validador -> validador.validar(idReclamacao, usuarioReclamadoId, descricaoResposta));


        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);
        var usuarioReclamado = usuarioRepository.getReferenceById(usuarioReclamadoId);


        reclamacao.responder(usuarioReclamado, descricaoResposta);
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

        if (!reclamacaoRepository.existsById(idReclamacao)) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        validadoresDeFechamentoDeReclamacao.forEach(validador -> validador.validar(idReclamacao, usuarioId));

        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);
        var administrador = usuarioRepository.findByIdAndIsAdmin(usuarioId, true);

        reclamacao.fechar(administrador);
        reclamacaoRepository.save(reclamacao);
    }

    public void grade(String idReclamacao, Integer notaFinal) {

        if (!reclamacaoRepository.existsById(idReclamacao)) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        validadoresDeNotaFinalReclamacao.forEach(validador -> validador.validar(idReclamacao, notaFinal));
        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);
        reclamacao.avaliar(notaFinal);
        reclamacaoRepository.save(reclamacao);
    }

}
