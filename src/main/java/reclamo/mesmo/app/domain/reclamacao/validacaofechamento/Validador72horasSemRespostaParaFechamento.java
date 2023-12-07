package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoClose;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validador72horasSemRespostaParaFechamento implements ValidadorFechamentoReclamacao {

    @Override
    public void validar(Reclamacao reclamacao, Usuario admin) {

        var dataAbertura = reclamacao.getDataReclamacao();
        var now = LocalDateTime.now();

        var tempoSemResposta = Duration.between(dataAbertura, now).toHours();

        if (tempoSemResposta < 72 && reclamacao.getStatusReclamacao().equals(EnumStatusReclamacao.ABERTA)) {
            throw new ValidacaoException("Uma reclamação sem resposta não pode ser fechada antes de 72 horas");
        }
    }
}
