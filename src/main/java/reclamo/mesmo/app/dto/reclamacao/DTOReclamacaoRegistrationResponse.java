package reclamo.mesmo.app.dto.reclamacao;

import reclamo.mesmo.app.domain.reclamacao.Reclamacao;

public record DTOReclamacaoRegistrationResponse(
        String id,
        String usuarioReclamanteId,
        String cpfCnpjReclamado,
        String descricaoReclamacao,
        String dataHoraReclamacao,
        String status
) {
    public DTOReclamacaoRegistrationResponse(Reclamacao reclamacao) {
        this(
                reclamacao.getId(),
                reclamacao.getUsuarioReclamante().getId(),
                reclamacao.getCpfCnpjReclamado(),
                reclamacao.getDescricaoReclamacao(),
                reclamacao.getDataReclamacao().toString(),
                reclamacao.getStatusReclamacao().toString()
        );
    }
}
