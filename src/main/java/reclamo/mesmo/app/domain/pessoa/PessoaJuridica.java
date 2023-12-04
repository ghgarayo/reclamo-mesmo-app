package reclamo.mesmo.app.domain.pessoa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRequest;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdateRequest;

@Table(name = "pessoa_juridica")
@Entity(name = "PessoaJuridica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PessoaJuridica {
    @Id
    private String id;

    @Column(name = "razao_social")
    private String razaoSocial;

    private String cnpj;
    private String email;
    private String senha;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Column(name = "is_active")
    private boolean isActive;

    public PessoaJuridica(DTOPessoaJuridicaRequest data) {
        this.id = java.util.UUID.randomUUID().toString();
        this.razaoSocial = data.razaoSocial();
        this.cnpj = data.cnpj();
        this.email = data.email();
        this.senha = data.senha();
        this.telefone = data.telefone();
        this.endereco = new Endereco(data.endereco());
        this.isActive = true;
    }

    public void update(DTOPessoaJuridicaUpdateRequest data) {
        if(data.razaoSocial() != null){
            this.razaoSocial = data.razaoSocial();
        }
        if(data.cpnj() != null){
            this.cnpj = data.cpnj();
        }
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.telefone() != null){
            this.telefone = data.telefone();
        }
        if(data.endereco() != null){
            this.endereco = new Endereco(data.endereco());
        }
    }

    public void inativar() {
        this.isActive = false;
    }

}
