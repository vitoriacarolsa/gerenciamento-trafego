package br.com.fiap.trafego.model;


import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SemaforoModel {

    @Expose(serialize = false)
    private Long id;
    @Expose
    private String localizacao;
    @Expose
    private String estadoAtual;
    @Expose
    private Integer tempoVerde;
    @Expose
    private Integer tempoVermelho;
    @Expose
    private String condicoesClimaticas;
    @Expose
    private LocalDateTime ultimaAtualizacao;

}
