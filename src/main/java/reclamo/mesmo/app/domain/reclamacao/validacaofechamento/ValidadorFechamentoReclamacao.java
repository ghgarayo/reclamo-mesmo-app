package reclamo.mesmo.app.domain.reclamacao.validacaofechamento;

public interface ValidadorFechamentoReclamacao {
    void validar(String idReclamacao, String usuarioId);
}
