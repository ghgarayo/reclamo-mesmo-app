package reclamo.mesmo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import reclamo.mesmo.app.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    UserDetails findByLogin(String login);

    @Query("""
               SELECT u
               FROM Usuario u
               WHERE u.id = :usuarioId AND u.isAdmin = :isAdmin
            """)
    Usuario findByUsuarioIdAndIsAdmin(String usuarioId, boolean isAdmin);
}
