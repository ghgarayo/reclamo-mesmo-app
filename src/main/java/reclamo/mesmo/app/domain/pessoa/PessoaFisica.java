package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reclamo.mesmo.app.domain.endereco.Endereco;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRequest;
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
    private String email;
    private String senha;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Column(name = "is_active")
    private boolean isActive;

    public PessoaFisica(DTOPessoaFisicaRequest dados) {
        this.id = UUID.randomUUID().toString();
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.isActive = true;
    }

    public void update(DTOPessoaFisicaUpdateRequest dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.email() != null){
            this.email = dados.email();
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



