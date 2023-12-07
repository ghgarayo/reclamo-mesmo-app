package reclamo.mesmo.app.domain.reclamacao.validacaocriacao;

import org.springframework.stereotype.Component;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoRegistration;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.infra.util.ValidadorCnpj;

@Component
public class ValidadorCnpjValido implements ValidadorCriacaoReclamacao{
    @Override
    public void validar(String usuarioReclamanteId, String cpfCnpjReclamado, String descricaoReclamacao) {

            if(cpfCnpjReclamado.length() == 14){
                var isCnpjValid = ValidadorCnpj.isCNPJ(cpfCnpjReclamado);
                if(!isCnpjValid) throw new ValidacaoException("CNPJ inválido!");
            }
    }
}
