package reclamo.mesmo.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, String> {
      Page<Reclamacao> findAllByStatusReclamacao(String statusReclamacao, Pageable pageable);

}
