package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.domain.usuario.Usuario;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaUpdateRequest;
import reclamo.mesmo.app.dto.usuario.DTOUsuarioRegisterRequest;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private UsuarioService usuarioService;

    public DTOPessoaFisicaResponse register(DTOPessoaFisicaRequest dto){
        var usuarioDTO = new DTOUsuarioRegisterRequest(dto.email(), dto.senha());
        var usuario = usuarioService.register(usuarioDTO);
        var pessoaFisica = new PessoaFisica(dto, usuario);
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

    public DTOPessoaFisicaResponse update(String id, DTOPessoaFisicaUpdateRequest dto) {
        var pessoaFisica = pessoaFisicaRepository.getReferenceById(id);
        pessoaFisica.update(dto);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

    public void inactivate(String id) {
        pessoaFisicaRepository.getReferenceById(id).inativar();
    }
}
