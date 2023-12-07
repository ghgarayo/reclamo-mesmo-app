package reclamo.mesmo.app.domain.reclamacao.validacaoresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoAnswerRequest;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Component
public class ValidadorUsuarioReclamadoAtivo implements ValidadorRespostaReclamacao {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DTOReclamacaoAnswerRequest dto) {
    }
}
