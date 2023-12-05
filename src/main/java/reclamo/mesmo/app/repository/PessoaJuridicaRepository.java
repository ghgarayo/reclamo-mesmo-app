package reclamo.mesmo.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
    Page<PessoaJuridica> findAllByIsActiveTrue(Pageable pageable);
    UserDetails findByEmail(String email);

}
