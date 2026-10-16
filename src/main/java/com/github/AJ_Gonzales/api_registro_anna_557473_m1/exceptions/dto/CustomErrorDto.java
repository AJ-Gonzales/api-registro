package com.github.AJ_Gonzales.api_registro_anna_557473_m1.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomErrorDto {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}
