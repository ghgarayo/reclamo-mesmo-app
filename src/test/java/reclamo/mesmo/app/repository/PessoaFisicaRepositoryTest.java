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
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.service.PessoaFisicaService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes da interface PessoaFisicaRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PessoaFisicaRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;


    @Test
    @DisplayName("Deve retornar uma lista de pessoas físicas ativas")
    void findAllByIsActiveTrue() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Fisica Teste",
                "68038149041",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaFisica2 = cadastrarPessoaFisica("Pessoa Fisica Teste 2",
                "29518649065",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaFisica3 = cadastrarPessoaFisica("Pessoa Fisica Teste 3",
                "66627246023",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());


        var pageable = PageRequest.of(0, 10);

        var pessoaFisicaList = pessoaFisicaRepository.findAllByIsActiveTrue(pageable);

        assertThat(pessoaFisicaList.getNumberOfElements()).isEqualTo(3);

    }

    @Test
    @DisplayName("Deve retornar uma lista vazia se as pessoas juridicas cadastradas estiverem inativas")
    void findAllByIsActiveTrueCenario2() {
        var pessoaFisica = cadastrarPessoaFisica("Pessoa Fisica Teste",
                "68038149041",
                "pessoa.fisica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());


        var pessoaFisica2 = cadastrarPessoaFisica("Pessoa Fisica Teste 2",
                "29518649065",
                "pessoa.fisica2@reclamomesmo.com",
                "123456",
                "47999999997",
                dadosEndereco());

        var pessoaFisica3 = cadastrarPessoaFisica("Pessoa Fisica Teste 3",
                "66627246023",
                "pessoa.fisica3@reclamomesmo.com",
                "123456",
                "47999999996",
                dadosEndereco());

        pessoaFisica.setActive(false);
        pessoaFisica2.setActive(false);
        pessoaFisica3.setActive(false);


        var pageable = PageRequest.of(0, 10);

        var pessoaFisicaList = pessoaFisicaRepository.findAllByIsActiveTrue(pageable);

        assertThat(pessoaFisicaList.getNumberOfElements()).isEqualTo(0);
    }

    private Usuario cadastrarUsuario(String login, String senha) {
        var usuario = new Usuario(login, senha, false);
        em.persist(usuario);
        return usuario;
    }

    private PessoaFisica cadastrarPessoaFisica(String nome, String cpf, String email, String senha, String telefone, DTOEndereco dadosEndereco) {
        var usuario = cadastrarUsuario(email, senha);
        var pessoaFisica = new PessoaFisica(nome, cpf, telefone, usuario, dadosEndereco);
        em.persist(pessoaFisica);
        return pessoaFisica;
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