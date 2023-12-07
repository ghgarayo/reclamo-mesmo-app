package reclamo.mesmo.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, String> {
    Page<PessoaFisica> findAllByIsActiveTrue(Pageable pageable);
    PessoaFisica findByCpf(String cpf);

}
