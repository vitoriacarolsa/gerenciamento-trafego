package br.com.fiap.trafego.services;

import br.com.fiap.trafego.dto.TrafegoDTO;
import br.com.fiap.trafego.entities.Trafego;
import br.com.fiap.trafego.repositories.TrafegoRepository;
import br.com.fiap.trafego.services.exceptions.ResourceNotFoundException;
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

    @Transactional
    public TrafegoDTO insert (TrafegoDTO dto){
        Trafego entity= new Trafego();
        copyDtoToEntity (dto, entity);
        entity= trafegoRepository.save(entity);
        return new TrafegoDTO(entity);
    }


    @Transactional(readOnly = true)
    public TrafegoDTO findById(Long id){
        Trafego trafego = trafegoRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new TrafegoDTO(trafego);
    }

    private void copyDtoToEntity(TrafegoDTO dto, Trafego entity){
       entity.setCongestionamento(dto.congestionamento());
       entity.setDataHora(dto.dataHora());
       entity.setLocalizacao(dto.localizacao());
       entity.setVelocidadeVeiculo(dto.velocidadeVeiculo());
       entity.setQuantidadeVeiculo(dto.quantidadeVeiculo());

    }
}
