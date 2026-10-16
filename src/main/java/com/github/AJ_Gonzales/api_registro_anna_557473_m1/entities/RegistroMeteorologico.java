package com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "registroId")
@Entity
@Table(name = "tb_registro")
public class RegistroMeteorologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registroId;

    @Column(name = "estacao_nome", nullable = false, length = 100)
    private String estacaoNome;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal temperatura;

    @Column(name = "percentual_umidade", nullable = false,
            precision = 5, scale = 2)
    private BigDecimal percentualUmidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precipitacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_climatica", nullable = false)
    private CondicaoClimatica condicaoClimatica;
}
