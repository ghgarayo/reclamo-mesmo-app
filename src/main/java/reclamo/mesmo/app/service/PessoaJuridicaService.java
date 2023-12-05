package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaJuridica;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaList;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaRequest;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaResponse;
import reclamo.mesmo.app.dto.pessoajuridica.DTOPessoaJuridicaUpdateRequest;
import reclamo.mesmo.app.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository repository;

    public DTOPessoaJuridicaResponse register(DTOPessoaJuridicaRequest dto ){
        var pessoaJuridica = new PessoaJuridica(dto);
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


    public DTOPessoaJuridicaResponse update(String id, DTOPessoaJuridicaUpdateRequest dto) {
        var pessoaJuridica = repository.getReferenceById(id);
        pessoaJuridica.update(dto);

        return new DTOPessoaJuridicaResponse(pessoaJuridica);
    }

    public void inactivate(String id) {
        repository.getReferenceById(id).inativar();
    }
}
