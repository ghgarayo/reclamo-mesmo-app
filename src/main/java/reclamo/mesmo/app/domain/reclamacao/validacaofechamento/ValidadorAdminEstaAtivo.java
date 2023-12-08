package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.ReclamacaoRepository;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Component
public class ValidadorAdminEstaAtivo implements ValidadorFechamentoReclamacao{


    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void validar(String idReclamacao, String usuarioId) {

        var isAdminActive = usuarioRepository.findByUsuarioIdAndIsActiveTrue(usuarioId);

        if (!isAdminActive.isAdmin()) {
            throw new ValidacaoException("O administrador não está ativo");
        }

    }
}
