package br.com.fiap.trafego.services;

import br.com.fiap.trafego.dto.SemaforoDTO;
import br.com.fiap.trafego.dto.TrafegoDTO;
import br.com.fiap.trafego.entities.Semaforo;
import br.com.fiap.trafego.entities.Trafego;
import br.com.fiap.trafego.repositories.TrafegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrafegoService {

    @Autowired
    private TrafegoRepository trafegoRepository;

    @Transactional(readOnly = true)
    public List<TrafegoDTO> findAll(){
        List<Trafego> result = trafegoRepository.findAll();
        return result.stream().map(x->new TrafegoDTO(x)).toList();
    }
}
