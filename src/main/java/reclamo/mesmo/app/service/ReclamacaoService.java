package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.reclamacao.validacao.ValidadorRespostaReclamacao;
import reclamo.mesmo.app.dto.reclamacao.*;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;
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
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private List<ValidadorRespostaReclamacao> validadoresDeRespostaDeReclamacao;

    public DTOReclamacaoRegistrationResponse register(DTOReclamacaoRegistrationRequest dto) {

        var usuarioReclamante = usuarioRepository.getReferenceById(dto.usuarioReclamanteId());
        var reclamacao = new Reclamacao(dto, usuarioReclamante);
        reclamacaoRepository.save(reclamacao);

        return new DTOReclamacaoRegistrationResponse(reclamacao);
    }

//    public DTOReclamacaoRegistrationResponse register(DTOReclamacaoRegistrationRequest dto) {
//        String cpfCnpjReclamado = dto.cpfCnpjReclamado();
//
//        Object pessoaId = pessoaFisicaRepository.findByCpf(cpfCnpjReclamado).getUsuario().getId();
//
//        if (pessoaId == null) {
//            pessoaId = pessoaJuridicaRepository.findByCnpj(cpfCnpjReclamado).getUsuario().getId();
//        }
//        if (pessoaId == null) {
//            pessoaId = "";
//        }
//
//        var usuarioReclamado = usuarioRepository.getReferenceById(pessoaId.toString());
//        var usuarioReclamante = usuarioRepository.getReferenceById(dto.usuarioReclamanteId());
//
//        var reclamacao = new Reclamacao(dto, usuarioReclamante, usuarioReclamado);
//        reclamacaoRepository.save(reclamacao);
//
//        return new DTOReclamacaoRegistrationResponse(reclamacao);
//    }


    public DTOReclamacaoAnswerResponse answer(DTOReclamacaoAnswerRequest dto) {

        if(!reclamacaoRepository.existsById(dto.idReclamacao())){
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

    public void close(String id) {
        var reclamacao = reclamacaoRepository.getReferenceById(id);
        reclamacao.fechar();
        reclamacaoRepository.save(reclamacao);
    }
}
