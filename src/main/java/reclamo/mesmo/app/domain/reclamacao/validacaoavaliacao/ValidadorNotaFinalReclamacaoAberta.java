package reclamo.mesmo.app.domain.reclamacao.validacaoavaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorNotaFinalReclamacaoAberta extends ValidadorNotaFinalReclamacao {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Override
    public void validar(String idReclamacao, Integer notaFinal) {
        if (!reclamacaoRepository.existsById(idReclamacao)) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);

        if(!reclamacao.getStatusReclamacao().equals(EnumStatusReclamacao.FECHADA)) {
            throw new ValidacaoException("Reclamação não está fechada");

        }
    }
}
