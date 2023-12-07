package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;


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

    public PessoaFisica(String nome, String cpf, String telefone, Usuario usuario, DTOEndereco endereco) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.usuario =  usuario;
        this.endereco = new Endereco(endereco);
        this.isActive = true;
    }

    public void update(String nome, String telefone, DTOEndereco endereco){
        if(nome!= null) this.nome = nome;
        if(telefone != null) this.telefone = telefone;
        if(endereco != null) this.endereco = new Endereco(endereco);
    }

    public void inativar() {
        this.isActive = false;
    }

}



