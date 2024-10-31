package br.com.fiap.trafego.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_semaforo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Semaforo {
    @Id
   @GeneratedValue
    (
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SEMAFORO"
    )
    @SequenceGenerator(
            name = "SEQ_SEMAFORO",
            sequenceName = "SEQ_SEMAFORO",
            allocationSize = 1
    )
    private Long id;
    private String localizacao;
    @Column(name = "estado_atual")
    private String estadoAtual;
    @Column(name = "tempo_verde")
    private Integer tempoVerde;
    @Column(name = "tempo_vermelho")
    private Integer tempoVermelho;
    @Column(name = "condicoes_climaticas")
    private String condicoesClimaticas;
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

}
