package reclamo.mesmo.app.domain.reclamacao.validacaoavaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorNotaFinalReclamacao implements ValidadorReclamacaoNotaFinal {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    public void validar(String idReclamacao, Integer notaFinal) {

        if (!reclamacaoRepository.existsById(idReclamacao)) {
            throw new ValidacaoException("Reclamação não encontrada");
        }

        if (notaFinal < 0 || notaFinal > 5) {
            throw new ValidacaoException("Nota final deve ser entre 0 e 5");
        }
    }

}
