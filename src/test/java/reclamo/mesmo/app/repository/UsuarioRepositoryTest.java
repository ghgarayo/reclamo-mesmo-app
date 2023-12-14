package reclamo.mesmo.app.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import reclamo.mesmo.app.domain.administrador.Administrador;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.service.PessoaFisicaService;
import reclamo.mesmo.app.service.PessoaJuridicaService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes da interface UsuarioRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;

    @MockBean
    private PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve retornar true se existir um usuário com o login informado e ele for um administrador")
    void findByUsuarioIdAndIsAdminCenario1() {
        var administrador = cadastrarAdministrador("Admin Teste",
                "admin.teste@reclamomesmo.com",
                "123456");

        var isAdmin = usuarioRepository.findByIdAndIsAdmin(administrador.getUsuario().getId(), true);

        assertThat(isAdmin).isNotNull();
    }


    @Test
    @DisplayName("Deve retornar null se existir um usuário pessoa fisica com o login informado e ele não for um administrador")
    void findByUsuarioIdAndIsAdminCenario2() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Física Teste",
                "70298572087",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco(),
                true);

        var isAdmin = usuarioRepository.findByIdAndIsAdmin(pessoaFisica.getUsuario().getId(), true);

        assertThat(isAdmin).isNull();
    }

    @Test
    @DisplayName("Deve retornar null se existir um usuário pessoa juridica com o login informado e ele não for um administrador")
    void findByUsuarioIdAndIsAdminCenario3() {
        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco(),
                true);

        var isAdmin = usuarioRepository.findByIdAndIsAdmin(pessoaJuridica.getUsuario().getId(), true);

        assertThat(isAdmin).isNull();
    }

    @Test
    @DisplayName("Deve retornar null se não existir um usuário com o login informado")
    void findByUsuarioIdAndIsActiveTrueCenario1() {
        var usuario = usuarioRepository.findByUsuarioIdAndIsActiveTrue("123456");

        assertThat(usuario).isNull();
    }

    @Test
    @DisplayName("Deve retornar null se existir um usuário pessoa fisica com o login informado mas ele não estiver ativo")
    void findByUsuarioIdAndIsActiveTrueCenario2() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Física Teste",
                "70298572087",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco(),
                true);

        pessoaFisicaService.inactivate(pessoaFisica.getId());

        var usuario = usuarioRepository.findByUsuarioIdAndIsActiveTrue(pessoaFisica.getUsuario().getId());

        assertThat(usuario).isNull();

    }

    @Test
    @DisplayName("Deve retornar null se existir um usuário pessoa juridica com o login informado mas ele não estiver ativo")
    void findByUsuarioIdAndIsActiveTrueCenario3() {
        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco(),
                true);

        pessoaJuridicaService.inactivate(pessoaJuridica.getId());

        var usuario = usuarioRepository.findByUsuarioIdAndIsActiveTrue(pessoaJuridica.getUsuario().getId());

        assertThat(usuario).isNull();
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

    private PessoaFisica cadastrarPessoaFisica(String nome, String cpf, String email, String senha, String telefone, DTOEndereco dadosEndereco, Boolean isActive) {
        var usuario = cadastrarUsuario(email, senha, false);
        var pessoaFisica = new PessoaFisica(nome, cpf, telefone, usuario, dadosEndereco);
        em.persist(pessoaFisica);
        return pessoaFisica;
    }

    private PessoaJuridica cadastrarPessoaJuridica(String nome, String cnpj, String email, String senha, String telefone, DTOEndereco dadosEndereco, Boolean isActive) {
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