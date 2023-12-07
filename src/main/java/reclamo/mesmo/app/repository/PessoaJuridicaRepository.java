package reclamo.mesmo.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
    Page<PessoaJuridica> findAllByIsActiveTrue(Pageable pageable);
    PessoaJuridica findByCnpj(String cnpj);
    PessoaJuridica findByUsuarioId(String usuarioId);

    PessoaJuridica isAdmin(String usuarioReclamanteId);
}
