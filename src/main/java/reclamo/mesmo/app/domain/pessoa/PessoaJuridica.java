package reclamo.mesmo.app.domain.pessoa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;

@Table(name = "pessoa_juridica")
@Entity(name = "PessoaJuridica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public PessoaJuridica(String razaoSocial, String cnpj, String telefone, Usuario usuario, DTOEndereco endereco) {
        this.id = java.util.UUID.randomUUID().toString();
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.usuario = usuario;
        this.endereco = new Endereco(endereco);
        this.isActive = true;
    }

    public void update(String razaoSocial, String telefone, DTOEndereco endereco){
        if(razaoSocial!= null) this.razaoSocial = razaoSocial;
        if(telefone != null) this.telefone = telefone;
        if(endereco != null) this.endereco = new Endereco(endereco);
    }

    public void inativar() {
        this.isActive = false;
    }

}
