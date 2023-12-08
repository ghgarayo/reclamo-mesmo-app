package reclamo.mesmo.app.domain.reclamacao;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.usuario.Usuario;

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

    @ManyToOne
    @JoinColumn(name = "fechado_por")
    private Usuario administrador;

    @Column(name = "nota_final")
    private Integer notaFinal;

    public Reclamacao(Usuario usuarioReclamante, String cpfCnpjReclamado, String descricaoReclamacao){
        this.id = UUID.randomUUID().toString();
        this.usuarioReclamante = usuarioReclamante;
        this.cpfCnpjReclamado = cpfCnpjReclamado;
        this.descricaoReclamacao = descricaoReclamacao;
        this.dataReclamacao = LocalDateTime.now();
        this.statusReclamacao = EnumStatusReclamacao.ABERTA;
    }

    public void responder(Usuario usuarioReclamado, String descricaoResposta){
        this.usuarioReclamado = usuarioReclamado;
        this.descricaoResposta = descricaoResposta;
        this.dataResposta = LocalDateTime.now();
        this.statusReclamacao = EnumStatusReclamacao.RESPONDIDA;
    }

    public void fechar(Usuario administrador){
        this.administrador = administrador;
        this.statusReclamacao = EnumStatusReclamacao.FECHADA;
    }

    public void avaliar(Integer notaFinal){
        this.notaFinal = notaFinal;
    }

}
