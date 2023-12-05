package reclamo.mesmo.app.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegisterRequest;

import java.util.UUID;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    private String id;
    private String login;
    private String senha;

    public Usuario(DTOUsuarioRegisterRequest dto) {
        this.id = UUID.randomUUID().toString();
        this.login = dto.email();
        this.senha = dto.senha();
    }

}
