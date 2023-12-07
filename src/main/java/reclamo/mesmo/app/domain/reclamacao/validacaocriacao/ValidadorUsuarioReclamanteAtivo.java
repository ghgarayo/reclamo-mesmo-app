package reclamo.mesmo.app.domain.reclamacao.validacaocriacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoRegistration;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;
import reclamo.mesmo.app.repository.UsuarioRepository;

@Component
public class ValidadorUsuarioReclamanteAtivo implements ValidadorCriacaoReclamacao{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao) {

//        var usuario =
//
//        if(usuario == null){
//            throw new ValidacaoException("O usuário reclamante não está ativo");
//        }
    }
}
