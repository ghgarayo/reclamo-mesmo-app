package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.endereco.Endereco;

@Table(name = "pessoa_fisica")
@Entity(name = "PessoaFisica")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PessoaFisica extends Pessoa{
    private String cpf;

    public PessoaFisica(String nome, String email, String telefone, String cpf, Endereco endereco) {
        super(nome, email, telefone, endereco);
        this.cpf = cpf;
        this.tipoPessoa = EnumTipoPessoa.PESSOA_FISICA;
    }
}
