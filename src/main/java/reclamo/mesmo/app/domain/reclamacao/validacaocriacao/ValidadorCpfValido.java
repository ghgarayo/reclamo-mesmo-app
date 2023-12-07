package reclamo.mesmo.app.domain.reclamacao.validacaocriacao;

import org.springframework.stereotype.Component;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoRegistration;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.infra.util.ValidadorCpf;

@Component
public class ValidadorCpfValido implements ValidadorCriacaoReclamacao {
    @Override
    public void validar(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao) {

        if (cpfCnpjReclamado.length() == 11) {
            var isCpfValid = ValidadorCpf.isCPF(cpfCnpjReclamado);
            if (!isCpfValid) throw new ValidacaoException("CPF inválido!");
        }

    }
}
