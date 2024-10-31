package br.com.fiap.trafego.controllers;

import br.com.fiap.trafego.dto.TrafegoDTO;
import br.com.fiap.trafego.services.TrafegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/trafegos")
public class TrafegoController {

    @Autowired
    private TrafegoService trafegoService;

    @GetMapping
    public ResponseEntity<List<TrafegoDTO>> findAll(){
        List<TrafegoDTO>list= trafegoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping (value= "/{id}")
    public ResponseEntity<TrafegoDTO> findById(@PathVariable long id){
        TrafegoDTO dto= trafegoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity <TrafegoDTO> insert(@Valid @RequestBody TrafegoDTO dto){
        dto= trafegoService.insert(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}


