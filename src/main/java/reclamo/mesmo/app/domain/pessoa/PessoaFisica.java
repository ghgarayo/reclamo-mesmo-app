package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRegistrationRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdateRequest;

import java.util.UUID;

@Table(name = "pessoa_fisica")
@Entity(name = "PessoaFisica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica {

    @Id
    private String id;
    private String nome;
    private String cpf;
    private String telefone;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Embedded
    private Endereco endereco;

    @Column(name = "is_active")
    private boolean isActive;

    public PessoaFisica(DTOPessoaFisicaRegistrationRequest dados, Usuario usuario) {
        this.id = UUID.randomUUID().toString();
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.usuario =  usuario;
        this.endereco = new Endereco(dados.endereco());
        this.isActive = true;
    }

    public void update(DTOPessoaFisicaUpdateRequest dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco = new Endereco(dados.endereco());
        }
    }

    public void inativar() {
        this.isActive = false;
    }

}



