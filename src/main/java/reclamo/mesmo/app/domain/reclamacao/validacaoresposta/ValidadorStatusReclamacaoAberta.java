package reclamo.mesmo.app.domain.reclamacao.validacaoresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorStatusReclamacaoAberta implements ValidadorRespostaReclamacao {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Override
    public void validar(String idReclamacao, String usuarioReclamadoId, String descricaoResposta) {

        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);

        if (!reclamacao.getStatusReclamacao().equals(EnumStatusReclamacao.ABERTA)) {
            throw new ValidacaoException("A reclamação não está aberta");
        }

    }
}