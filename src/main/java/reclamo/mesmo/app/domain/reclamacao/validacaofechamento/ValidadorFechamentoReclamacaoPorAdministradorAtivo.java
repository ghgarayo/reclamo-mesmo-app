package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoClose;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.AdministradorRepository;

@Component
public class ValidadorFechamentoReclamacaoPorAdministradorAtivo implements ValidadorFechamentoReclamacao {

    @Autowired
    private AdministradorRepository administradorRepository;

    public void validar(Reclamacao reclamacao, Usuario admin) {

        var administrador = administradorRepository.findByUsuarioId(admin.getId());

        if (administrador == null) {
            throw new ValidacaoException("O usuário não é um administrador");
        }

        if (!administrador.isActive()) {
            throw new ValidacaoException("O usuário administrador não está ativo");
        }
    }
}
