package com.cdt.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PacienteDto {

    private Integer id;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    @Size(min = 3, message = "Atributo debe tener min 3 caracteres")
    private String nombre;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    @Size(min = 3, message = "Atributo debe tener min 3 caracteres")
    private String apellido;

    @NotBlank(message = "Atributo no puede ser null o vacio")
    @Size(min = 3, message = "Atributo debe tener min 3 caracteres")
    private String dni;

    private LocalDate fechaCreacion;

    @NotNull(message = "Atributo no puede ser null o vacio")
    @Valid
    private DomicilioDto domicilioDto;

}
