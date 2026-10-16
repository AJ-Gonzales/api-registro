package com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto;

import com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities.CondicaoClimatica;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities.RegistroMeteorologico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistroResponseDto {

    private Long registroId;
    private String estacaoNome;
    private LocalDate dataRegistro;
    private BigDecimal temperatura;
    private BigDecimal percentualUmidade;
    private BigDecimal precipitacao;

    private CondicaoClimatica condicaoClimatica;

    public RegistroResponseDto(RegistroMeteorologico registro) {
        registroId = registro.getRegistroId();
        estacaoNome = registro.getEstacaoNome();
        dataRegistro = registro.getDataRegistro();
        temperatura = registro.getTemperatura();
        percentualUmidade = registro.getPercentualUmidade();
        precipitacao = registro.getPrecipitacao();
        condicaoClimatica = registro.getCondicaoClimatica();
    }
}
