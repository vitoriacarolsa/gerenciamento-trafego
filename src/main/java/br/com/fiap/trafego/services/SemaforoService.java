package br.com.fiap.trafego.services;

import br.com.fiap.trafego.dto.SemaforoDTO;
import br.com.fiap.trafego.entities.Semaforo;
import br.com.fiap.trafego.repositories.SemaforoRepository;
import br.com.fiap.trafego.services.exceptions.DatabaseException;
import br.com.fiap.trafego.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SemaforoService {

    @Autowired
    private SemaforoRepository semaforoRepository;

    @Transactional(readOnly = true)
    public SemaforoDTO findById(Long id){
        Semaforo semaforo = semaforoRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new SemaforoDTO(semaforo);
    }

    @Transactional (readOnly = true)
    public List<SemaforoDTO> findAll(){
        List<Semaforo> result = semaforoRepository.findAll();
        return result.stream().map(x->new SemaforoDTO(x)).toList();
    }

    @Transactional
    public SemaforoDTO insert (SemaforoDTO dto){
        Semaforo entity= new Semaforo();
        copyDtoToEntity (dto, entity);
        entity= semaforoRepository.save(entity);
        return new SemaforoDTO(entity);
    }

    @Transactional
    public SemaforoDTO update (Long id, SemaforoDTO dto){
        try{
            Semaforo entity= semaforoRepository.getReferenceById(id);
            copyDtoToEntity (dto, entity);
            entity= semaforoRepository.save(entity);
            return new SemaforoDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!semaforoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            semaforoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(SemaforoDTO dto, Semaforo entity){
        entity.setLocalizacao(dto.localizacao());
        entity.setEstadoAtual(dto.estadoAtual());
        entity.setCondicoesClimaticas(dto.condicoesClimaticas());
        entity.setTempoVerde(dto.tempoVerde());
        entity.setTempoVermelho(dto.tempoVermelho());
        entity.setUltimaAtualizacao(dto.ultimaAtualizacao());

    }
}
