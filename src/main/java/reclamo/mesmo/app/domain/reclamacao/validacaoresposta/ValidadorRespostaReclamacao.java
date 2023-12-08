package reclamo.mesmo.app.domain.reclamacao.validacaoresposta;

public interface ValidadorRespostaReclamacao {
    void validar(String idReclamacao, String usuarioReclamadoId, String descricaoResposta);
}
