package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.infra.util.ValidadorCpf;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public DTOPessoaFisicaResponse register(String nome, String cpf, String email, String senha, String telefone, DTOEndereco endereco){

        var isCpfValid = ValidadorCpf.isCPF(cpf);

        if(!isCpfValid) throw new ValidacaoException("CPF inválido!");

        var usuario = usuarioService.register(email, senha, false);
        var pessoaFisica = new PessoaFisica(nome, cpf, telefone, usuario, endereco);
        pessoaFisicaRepository.save(pessoaFisica);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

    public Page<DTOPessoaFisicaList> readAll(Pageable pageable){

        return pessoaFisicaRepository.findAllByIsActiveTrue(pageable).map(DTOPessoaFisicaList::new);
    }

    public DTOPessoaFisicaResponse readById(String id){
        var pessoaFisica = pessoaFisicaRepository.getReferenceById(id);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

    public DTOPessoaFisicaResponse update(String id, String nome, String telefone, DTOEndereco endereco){

        var pessoaFisica = pessoaFisicaRepository.getReferenceById(id);
        pessoaFisica.update(nome, telefone, endereco);
        pessoaFisicaRepository.save(pessoaFisica);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

    public void inactivate(String id) {

        var pessoaFisica = pessoaFisicaRepository.getReferenceById(id);
        pessoaFisica.inativar();
        pessoaFisicaRepository.save(pessoaFisica);
    }
}
