package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.dto.endereco.DTOEndereco;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaList;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.infra.exception.ValidacaoException;
import reclamo.mesmo.app.infra.util.ValidadorCnpj;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {
    @Autowired
    private PessoaJuridicaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public DTOPessoaJuridicaResponse register(String razaoSocial, String cnpj, String email, String senha, String telefone, DTOEndereco endereco){

        var isCnpjValid = ValidadorCnpj.isCNPJ(cnpj);

        if(!isCnpjValid) throw new ValidacaoException("CNPJ inválido!");

        var usuario = usuarioService.register(email, senha, false);
        var pessoaJuridica = new PessoaJuridica(razaoSocial, cnpj, telefone, usuario, endereco);
        repository.save(pessoaJuridica);

        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public Page<DTOPessoaJuridicaList> readAll(Pageable pageable){
        return repository.findAllByIsActiveTrue(pageable).map(DTOPessoaJuridicaList::new);
    }

    public DTOPessoaJuridicaResponse readById(String id) {
        var pessoaJuridica = repository.getReferenceById(id);
        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public DTOPessoaJuridicaResponse update(String id, String razaoSocial, String telefone, DTOEndereco endereco) {

        var pessoaJuridica = repository.getReferenceById(id);
        pessoaJuridica.update(razaoSocial, telefone, endereco);
        repository.save(pessoaJuridica);
        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public void inactivate(String id) {

        var pessoaJuridica = repository.getReferenceById(id);
        pessoaJuridica.inativar();
        repository.save(pessoaJuridica);
    }
}
