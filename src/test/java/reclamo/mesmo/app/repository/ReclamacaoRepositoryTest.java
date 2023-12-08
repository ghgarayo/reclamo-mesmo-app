package reclamo.mesmo.app.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import reclamo.mesmo.app.domain.administrador.Administrador;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.domain.reclamacao.EnumStatusReclamacao;
import reclamo.mesmo.app.domain.reclamacao.Reclamacao;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.service.PessoaFisicaService;
import reclamo.mesmo.app.service.PessoaJuridicaService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes da interface RecalamaçãoRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ReclamacaoRepositoryTest {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;

    @MockBean
    private PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve retornar uma lista de reclamações abertas")
    void findAllByStatusReclamacaoCenario1() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Física Teste",
                "70298572087",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 1");
        cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 2");
        cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 3");

        var pageable = PageRequest.of(0, 10);
        var reclamacoes = reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.ABERTA, pageable);

        assertThat(reclamacoes.getNumberOfElements()).isEqualTo(3);

    }

    @Test
    @DisplayName("Deve retornar uma lista de reclamações respondidas")
    void findAllByStatusReclamacaoCenario2() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Física Teste",
                "70298572087",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var reclamacao1 = cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 1");
        var reclamacao2 = cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 2");
        cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 3");

        reclamacao1.responder(pessoaJuridica.getUsuario(), "Resposta 1");
        reclamacao2.responder(pessoaJuridica.getUsuario(), "Resposta 2");

        var pageable = PageRequest.of(0, 10);
        var reclamacoes = reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.RESPONDIDA, pageable);

        assertThat(reclamacoes.getNumberOfElements()).isEqualTo(2);

    }

    @Test
    @DisplayName("Deve retornar uma lista de reclamações fechadas")
    void findAllByStatusReclamacaoCenario3() {
        var administrador = cadastrarAdministrador("Admin Teste",
                "admin.teste@reclamomesmo.com",
                "123456");


        var pessoaFisica = cadastrarPessoaFisica("Pessoa Física Teste",
                "70298572087",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        System.out.println(pessoaFisica.getUsuario().getLogin());
        System.out.println(pessoaJuridica.getUsuario().getLogin());
        System.out.println(administrador.getUsuario().getLogin());


        var reclamacao1 = cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 1");
        var reclamacao2 = cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 2");
        var reclamacao3 = cadastrarReclamacao(pessoaFisica.getUsuario(), pessoaJuridica.getCnpj(), "Teste de reclamação 3");

        reclamacao1.responder(pessoaJuridica.getUsuario(), "Resposta 1");
        reclamacao2.responder(pessoaJuridica.getUsuario(), "Resposta 2");

        reclamacao1.fechar(administrador.getUsuario());

        var pageable = PageRequest.of(0, 10);
        var reclamacoes = reclamacaoRepository.findAllByStatusReclamacao(EnumStatusReclamacao.FECHADA, pageable);
        assertThat(reclamacoes.getNumberOfElements()).isEqualTo(1);
    }


    private Reclamacao cadastrarReclamacao(Usuario reclamante, String cpfCnpjReclamado, String descricaoReclamacao) {
        var reclamacao = new Reclamacao(reclamante, cpfCnpjReclamado, descricaoReclamacao);
        em.persist(reclamacao);
        return reclamacao;
    }


    private Usuario cadastrarUsuario(String login, String senha, Boolean isAdmin) {
        var usuario = new Usuario(login, senha, isAdmin);
        em.persist(usuario);
        return usuario;
    }

    private Administrador cadastrarAdministrador(String nome, String login, String senha) {
        var usuario = new Usuario(login, senha, true);
        var administrador = new Administrador(nome, usuario);
        em.persist(administrador);
        return administrador;
    }

    private PessoaFisica cadastrarPessoaFisica(String nome, String cpf, String email, String senha, String telefone, DTOEndereco dadosEndereco) {
        var usuario = cadastrarUsuario(email, senha, false);
        var pessoaFisica = new PessoaFisica(nome, cpf, telefone, usuario, dadosEndereco);
        em.persist(pessoaFisica);
        return pessoaFisica;
    }

    private PessoaJuridica cadastrarPessoaJuridica(String nome, String cnpj, String email, String senha, String telefone, DTOEndereco dadosEndereco) {
        var usuario = cadastrarUsuario(email, senha, false);
        var pessoaJuridica = new PessoaJuridica(nome, cnpj, telefone, usuario, dadosEndereco);
        em.persist(pessoaJuridica);
        return pessoaJuridica;
    }

    private DTOEndereco dadosEndereco() {
        return new DTOEndereco(
                "rua teste",
                "bairro",
                null,
                null,
                "00000000",
                "Blumenau",
                "SC"
        );
    }


}