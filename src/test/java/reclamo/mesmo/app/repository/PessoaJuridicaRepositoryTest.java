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
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.service.PessoaJuridicaService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes da interface PessoaJuridicaRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PessoaJuridicaRepositoryTest {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private TestEntityManager em;

    @MockBean
    private PessoaJuridicaService pessoaJuridicaService;

    @Test
    @DisplayName("Deve retornar uma lista de pessoas jurídicas ativas")
    void findAllByIsActiveTrueCenario1() {
        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica2 = cadastrarPessoaJuridica("Pessoa Jurídica Teste2",
                "57853707000168",
                "pessoa.juridica2@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica3 = cadastrarPessoaJuridica("Pessoa Jurídica Teste3",
                "81897677000176",
                "pessoa.juridica3@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pageable = PageRequest.of(0, 10);

        var pessoaJuridicaList = pessoaJuridicaRepository.findAllByIsActiveTrue(pageable);

        assertThat(pessoaJuridicaList.getNumberOfElements()).isEqualTo(3);
    }


    @Test
    @DisplayName("Deve retornar uma lista vazia se as pessoas juridicas cadastradas estiverem inativas")
    void findAllByIsActiveTrueCenario2() {
        // Creating entities with isActive set to true
        var pessoaJuridica = cadastrarPessoaJuridica("Pessoa Jurídica Teste",
                "70298572000187",
                "pessoa.juridica@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica2 = cadastrarPessoaJuridica("Pessoa Jurídica Teste2",
                "57853707000168",
                "pessoa.juridica2@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        var pessoaJuridica3 = cadastrarPessoaJuridica("Pessoa Jurídica Teste3",
                "81897677000176",
                "pessoa.juridica3@reclamomesmo.com",
                "123456",
                "47999999999",
                dadosEndereco());

        pessoaJuridica.setActive(false);
        pessoaJuridica2.setActive(false);
        pessoaJuridica3.setActive(false);

        pessoaJuridicaRepository.save(pessoaJuridica);
        pessoaJuridicaRepository.save(pessoaJuridica2);
        pessoaJuridicaRepository.save(pessoaJuridica3);

        var pageable = PageRequest.of(0, 10);
        var pessoaJuridicaList = pessoaJuridicaRepository.findAllByIsActiveTrue(pageable);

        assertThat(pessoaJuridicaList.getNumberOfElements()).isEqualTo(0);
    }




    private Usuario cadastrarUsuario(String login, String senha, Boolean isAdmin) {
        var usuario = new Usuario(login, senha, isAdmin);
        em.persist(usuario);
        return usuario;
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