package reclamo.mesmo.app.domain.reclamacao.validacaoresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorReclamadoIgualAoDaReclamacao implements ValidadorRespostaReclamacao {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    public void validar(String idReclamacao, String usuarioReclamadoId, String descricaoResposta) {

        var reclamacao = reclamacaoRepository.getReferenceById(idReclamacao);
        var cpfOuCpnj = reclamacao.getCpfCnpjReclamado();
        Usuario usuario;

        if (cpfOuCpnj.length() == 11) {
            usuario = pessoaFisicaRepository.findByCpf(cpfOuCpnj).getUsuario();
        } else {
            usuario = pessoaJuridicaRepository.findByCnpj(cpfOuCpnj).getUsuario();
        }
        if (!usuario.getId().equals(usuarioReclamadoId)) {
            throw new ValidacaoException("O usuário reclamado não é o mesmo da reclamação");
        }
    }


}
