package br.com.fiap.trafego.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_trafego")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Trafego {

    @Id
    @GeneratedValue
//            (
//            strategy = GenerationType.SEQUENCE,
//            generator = "SEQ_TRAFEGO"
//    )
//    @SequenceGenerator(
//            name = "SEQ_TRAFEGO",
//            sequenceName = "SEQ_TRAFEGO",
//            allocationSize = 1
//    )
    private Long id;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    private String localizacao;
    @Column(name = "quantidade_veiculo")
    private Integer quantidadeVeiculo;
    @Column(name = "velocidade_veiculo")
    private Double velocidadeVeiculo;
    private Boolean congestionamento;

}
