package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorFechamentoReclamacaoFechada implements ValidadorFechamentoReclamacao{

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Override
    public void validar(String idReclamacao, String usuarioId) {

        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);

        if(reclamacao.getStatusReclamacao().equals(EnumStatusReclamacao.FECHADA)) {
            throw new ValidacaoException("A reclamação já está fechada");
        }


    }
}
