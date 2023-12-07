package reclamo.mesmo.app.domain.reclamacao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoRegistrationRequest;
import reclamo.mesmo.app.dto.reclamacao.DTOReclamacaoAnswerRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "reclamacoes")
@Entity(name = "Reclamacao")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reclamacao {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "reclamante_usuario_id")
    private Usuario usuarioReclamante;

    @Column(name = "cpf_cnpj_reclamado")
    private String cpfCnpjReclamado;

    @ManyToOne
    @JoinColumn(name = "reclamado_usuario_id")
    private Usuario usuarioReclamado;

    @Column(name = "descricao_reclamacao")
    private String descricaoReclamacao;

    @Column(name = "data_reclamacao")
    private LocalDateTime dataReclamacao;

    @Column(name = "descricao_resposta")
    private String descricaoResposta;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;

    @Column(name = "status_reclamacao")
    @Enumerated(EnumType.STRING)
    private EnumStatusReclamacao statusReclamacao;


    public Reclamacao(DTOReclamacaoRegistrationRequest dto, Usuario usuarioReclamante){
        this.id = UUID.randomUUID().toString();
        this.usuarioReclamante = usuarioReclamante;
        this.cpfCnpjReclamado = dto.cpfCnpjReclamado();
        this.descricaoReclamacao = dto.descricaoReclamacao();
        this.dataReclamacao = LocalDateTime.now();
        this.statusReclamacao = EnumStatusReclamacao.ABERTA;
    }

    public Reclamacao(DTOReclamacaoRegistrationRequest dto, Usuario usuarioReclamante, Usuario usuarioReclamado){
        this.id = UUID.randomUUID().toString();
        this.usuarioReclamante = usuarioReclamante;
        this.cpfCnpjReclamado = dto.cpfCnpjReclamado();
        this.usuarioReclamado = usuarioReclamado;
        this.descricaoReclamacao = dto.descricaoReclamacao();
        this.dataReclamacao = LocalDateTime.now();
        this.statusReclamacao = EnumStatusReclamacao.ABERTA;
    }

    public void responder(DTOReclamacaoAnswerRequest dto, Usuario usuarioReclamado){
        if(this.usuarioReclamado == null){
            this.usuarioReclamado = usuarioReclamado;
        }
        if(this.usuarioReclamado != usuarioReclamado){
            throw new RuntimeException("Usuário não tem permissão para responder essa reclamação");
        }
        this.descricaoResposta = dto.descricaoResposta();
        this.dataResposta = LocalDateTime.now();
        this.statusReclamacao = EnumStatusReclamacao.RESPONDIDA;
    }

    public void fechar(){
        this.statusReclamacao = EnumStatusReclamacao.FECHADA;
    }

    //TODO - AJUSTAR FINALIZAÇÃO DE RECLAMAÇÃO PARA CONTER INFORMAÇÃO DO ADMINISTRADOR QUE FINALIZOU

}
