package reclamo.mesmo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reclamo.mesmo.app.domain.administrador.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, String> {
}
