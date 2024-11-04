package br.com.fiap.trafego.controllers;

import br.com.fiap.trafego.dto.UsuarioExibicaoDTO;
import br.com.fiap.trafego.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping (value= "/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping (value= "/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> update (@PathVariable long id, @Valid @RequestBody UsuarioExibicaoDTO dto){
        dto= usuarioService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}