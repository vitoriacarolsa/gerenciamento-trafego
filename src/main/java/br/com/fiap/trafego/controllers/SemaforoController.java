package br.com.fiap.trafego.controllers;

import br.com.fiap.trafego.dto.SemaforoDTO;
import br.com.fiap.trafego.services.SemaforoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/semaforos")
public class SemaforoController {

    @Autowired
    private SemaforoService semaforoService;

    @GetMapping (value= "/{id}")
    public ResponseEntity<SemaforoDTO> findById(@PathVariable long id){
        SemaforoDTO dto= semaforoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SemaforoDTO>> findAll(){
        List<SemaforoDTO>list= semaforoService.findAll();
        return ResponseEntity.ok(list);
    }


    @PostMapping
    public ResponseEntity <SemaforoDTO> insert(@Valid @RequestBody SemaforoDTO dto){
        dto= semaforoService.insert(dto);
//        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(dto.id()).toUri();
//        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping (value= "/{id}")
    public ResponseEntity<SemaforoDTO> update (@PathVariable long id,@Valid @RequestBody SemaforoDTO dto){
        dto= semaforoService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping (value= "/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id){
        semaforoService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
