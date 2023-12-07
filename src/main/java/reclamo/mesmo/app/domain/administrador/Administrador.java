package reclamo.mesmo.app.domain.administrador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.administrador.DTOAdministradorRegistration;

import java.util.UUID;

@Table(name = "administrador")
@Entity(name = "Administrador")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Administrador {

    @Id
    private String id;
    private String nome;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "is_active")
    private boolean isActive;

    public Administrador(String nome, Usuario usuario) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.usuario = usuario;
        this.isActive = true;
    }
}
