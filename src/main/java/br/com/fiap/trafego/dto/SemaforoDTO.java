package br.com.fiap.trafego.dto;



import br.com.fiap.trafego.entities.Semaforo;

import java.time.LocalDateTime;

public record SemaforoDTO(
         Long id,
         String localizacao,
         String estadoAtual,
         Integer tempoVerde,
         Integer tempoVermelho,
         String condicoesClimaticas,
         LocalDateTime ultimaAtualizacao
) {
    public SemaforoDTO(Semaforo semaforo){
        this(
         semaforo.getId(),
         semaforo.getLocalizacao(),
         semaforo.getEstadoAtual(),
         semaforo.getTempoVerde(),
         semaforo.getTempoVermelho(),
         semaforo.getCondicoesClimaticas(),
         semaforo.getUltimaAtualizacao()
        );
    }
}
