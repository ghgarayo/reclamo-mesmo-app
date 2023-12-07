package reclamo.mesmo.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, String> {
      Page<Reclamacao> findAllByStatusReclamacao(EnumStatusReclamacao statusReclamacao, Pageable pageable);

}
