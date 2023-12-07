package reclamo.mesmo.app.domain.reclamacao.validacaoresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoAnswerRequest;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;
import reclamo.mesmo.app.repository.ReclamacaoRepository;

@Component
public class ValidadorReclamadoIgualAoDaReclamacao implements ValidadorRespostaReclamacao {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Override
    public void validar(DTOReclamacaoAnswerRequest dto) {

        var reclamacao = reclamacaoRepository.getReferenceById(dto.idReclamacao());
        var cpfOuCpnj = reclamacao.getCpfCnpjReclamado();

        if (cpfOuCpnj.length() == 11) {
            var pessoaFisica = pessoaFisicaRepository.findByCpf(cpfOuCpnj);
            var usuarioReclamado = pessoaFisica.getUsuario();
            if (!usuarioReclamado.getId().equals(dto.usuarioReclamadoId())) {
                throw new ValidacaoException("O usuário reclamado não é o mesmo da reclamação");
            }
        }

        if (cpfOuCpnj.length() == 14) {
            var pessoaJuridica = pessoaJuridicaRepository.findByCnpj(cpfOuCpnj);
            var usuarioReclamado = pessoaJuridica.getUsuario();
            if (!usuarioReclamado.getId().equals(dto.usuarioReclamadoId())) {
                throw new ValidacaoException("O usuário reclamado não é o mesmo da reclamação");
            }
        }
    }

    @Component
    public static class ValidadorStatusReclamacaoAberta implements ValidadorRespostaReclamacao {

        @Autowired
        private ReclamacaoRepository reclamacaoRepository;
        @Override
        public void validar(DTOReclamacaoAnswerRequest dto) {

            var reclamacao = reclamacaoRepository.getReferenceById(dto.idReclamacao());

            if (!reclamacao.getStatusReclamacao().equals(EnumStatusReclamacao.ABERTA)) {
                throw new ValidacaoException("A reclamação não está aberta");
            }

        }
    }
}
