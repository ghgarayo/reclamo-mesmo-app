package reclamo.mesmo.app.domain.pessoa;

import jakarta.persistence.*;
import reclamo.mesmo.app.domain.endereco.Endereco;

import java.util.UUID;

abstract class Pessoa {
    @Id
    private String id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa")
    EnumTipoPessoa tipoPessoa;
    @Embedded
    private Endereco endereco;
    @Column(name = "is_active")
    private boolean isActive;


    public Pessoa() {
    }
    public Pessoa(String nome, String email, String telefone, Endereco endereco) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.isActive = true;
    }


}



