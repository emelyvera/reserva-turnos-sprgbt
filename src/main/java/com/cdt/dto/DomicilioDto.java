package com.cdt.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DomicilioDto {

    private Integer id;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    private String calle;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    private String numero;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    private String localidad;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    private String provincia;
}
