package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaList;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRegistrationRequest;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdateRequest;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegistrationRequest;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {
    @Autowired
    private PessoaJuridicaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public DTOPessoaJuridicaResponse register(DTOPessoaJuridicaRegistrationRequest dto ){
        var usuarioDTO = new DTOUsuarioRegistrationRequest(dto.email(), dto.senha(), false);
        var usuario = usuarioService.register(usuarioDTO);
        var pessoaJuridica = new PessoaJuridica(dto, usuario);
        repository.save(pessoaJuridica);

        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public Page<DTOPessoaJuridicaList> readAll(Pageable pageable){
        //TODO: ALTERAR PARA QUE RETORNE O EMAIL DOS USUÁRIOS OBS: NÃO É NECESSÁRIO RETORNAR A SENHA
        return repository.findAllByIsActiveTrue(pageable).map(DTOPessoaJuridicaList::new);
    }

    public DTOPessoaJuridicaResponse readById(String id) {
        //TODO: ALTERAR PARA QUE RETORNE OS DADOS DO USUARIO(EMAIL E SE É ADMINISTRADOR) OBS: NÃO É NECESSÁRIO RETORNAR A SENHA
        var pessoaJuridica = repository.getReferenceById(id);

        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }


    public DTOPessoaJuridicaResponse update(String id, DTOPessoaJuridicaUpdateRequest dto) {
        var pessoaJuridica = repository.getReferenceById(id);
        pessoaJuridica.update(dto);

        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public void inactivate(String id) {
        repository.getReferenceById(id).inativar();
    }
}
