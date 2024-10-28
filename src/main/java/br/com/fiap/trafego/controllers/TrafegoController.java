package br.com.fiap.trafego.controllers;

import br.com.fiap.trafego.dto.TrafegoDTO;
import br.com.fiap.trafego.services.TrafegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


