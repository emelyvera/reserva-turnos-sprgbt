package com.cdt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoDto {

    private Integer id;

    @NotNull(message = "Atributo no puede ser null")
    private OdontologoDto odontologoDto;

    @NotNull(message = "Atributo no puede ser null")
    private PacienteDto pacienteDto;

    @NotNull(message = "Atributo no puede ser null")
    private LocalDateTime fechaHora;
}
