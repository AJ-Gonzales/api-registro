package com.github.AJ_Gonzales.api_registro_anna_557473_m1.dto;

import com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities.CondicaoClimatica;
import com.github.AJ_Gonzales.api_registro_anna_557473_m1.entities.RegistroMeteorologico;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistroRequestDto {

    @NotBlank(message = "O nome da estação é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String estacaoNome;

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate dataRegistro;

    @NotNull(message = "A temperatura é obrigatória")
    private BigDecimal temperatura;

    @NotNull(message = "A umidade é obrigatória")
    @Size(min = 0,max = 100, message = "A umidade deve estar entre 0 a 100")
    private BigDecimal percentualUmidade;

    @NotNull(message = "A precipitação é obrigatória")
    @PositiveOrZero(message = "A precipitação deve ser igual ou maior que zero")
    private BigDecimal precipitacao;

    @NotEmpty(message = "A condição climática é obrigatória")
    private CondicaoClimatica condicaoClimatica;

    public RegistroRequestDto(RegistroMeteorologico registro) {
        estacaoNome = registro.getEstacaoNome();
        dataRegistro = registro.getDataRegistro();
        temperatura = registro.getTemperatura();
        percentualUmidade = registro.getPercentualUmidade();
        precipitacao = registro.getPrecipitacao();
        condicaoClimatica = registro.getCondicaoClimatica();
    }
}
