package reclamo.mesmo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.pessoa.PessoaFisica;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaList;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaRequest;
import reclamo.mesmo.app.dto.pessoafisica.DTOPessoaFisicaResponse;
import reclamo.mesmo.app.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository repository;

    public DTOPessoaFisicaResponse register(DTOPessoaFisicaRequest dto ){
        var pessoaFisica = new PessoaFisica(dto);
        repository.save(pessoaFisica);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

    public Page<DTOPessoaFisicaList> readAll(Pageable pageable){
        return repository.findAllByIsActiveTrue(pageable).map(DTOPessoaFisicaList::new);
    }

    public DTOPessoaFisicaResponse readById(String id){
        var pessoaFisica = repository.findById(id).orElse(null);

        return new DTOPessoaFisicaResponse(pessoaFisica);
    }

}
