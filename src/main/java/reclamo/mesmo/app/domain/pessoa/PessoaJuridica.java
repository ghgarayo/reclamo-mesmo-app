package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.endereco.Endereco;

@Table(name = "pessoa_juridica")
@Entity(name = "PessoaJuridica")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PessoaJuridica extends Pessoa{

    private String cnpj;

    public PessoaJuridica(String nome, String email, String telefone, String cnpj, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cnpj = cnpj;
        this.tipoPessoa = EnumTipoPessoa.PESSOA_JURIDICA;
    }


}
