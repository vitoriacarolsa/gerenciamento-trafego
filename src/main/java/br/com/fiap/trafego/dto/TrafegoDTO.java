package br.com.fiap.trafego.dto;

import br.com.fiap.trafego.entities.Trafego;

import java.time.LocalDateTime;

public record TrafegoDTO(
       Long id,
       LocalDateTime dataHora,
       String localizacao,
       Integer quantidadeVeiculo,
       Double velocidadeVeiculo,
       Boolean congestionamento
) {
    public TrafegoDTO(Trafego trafego){
        this(
        trafego.getId(),
        trafego.getDataHora(),
        trafego.getLocalizacao(),
        trafego.getQuantidadeVeiculo(),
        trafego.getVelocidadeVeiculo(),
        trafego.getCongestionamento()

        );
    }
}
