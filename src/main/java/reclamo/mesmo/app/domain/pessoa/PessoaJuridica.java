package reclamo.mesmo.app.domain.pessoa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.usuario.Usuario;
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
    private String telefone;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Embedded
    private Endereco endereco;

    @Column(name = "is_active")
    private boolean isActive;

    public PessoaJuridica(DTOPessoaJuridicaRequest dto, Usuario usuario) {
        this.id = java.util.UUID.randomUUID().toString();
        this.razaoSocial = dto.razaoSocial();
        this.cnpj = dto.cnpj();
        this.usuario = usuario;
        this.telefone = dto.telefone();
        this.endereco = new Endereco(dto.endereco());
        this.isActive = true;
    }

    public void update(DTOPessoaJuridicaUpdateRequest dto) {
        if(dto.razaoSocial() != null){
            this.razaoSocial = dto.razaoSocial();
        }
        if(dto.cpnj() != null){
            this.cnpj = dto.cpnj();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if(dto.endereco() != null){
            this.endereco = new Endereco(dto.endereco());
        }
    }

    public void inativar() {
        this.isActive = false;
    }

}
