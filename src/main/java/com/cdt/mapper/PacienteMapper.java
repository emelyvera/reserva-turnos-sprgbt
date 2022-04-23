package com.cdt.mapper;

import com.cdt.dto.DomicilioDto;
import com.cdt.dto.PacienteDto;
import com.cdt.entities.Domicilio;
import com.cdt.entities.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class PacienteMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DomicilioMapper domicilioMapper;

    public PacienteDto mapDTO(Paciente pacienteEntity) {

        DomicilioDto domicilioDto = domicilioMapper.mapDTO(pacienteEntity.getDomicilio());
        PacienteDto pacienteDto = modelMapper.map(pacienteEntity, PacienteDto.class);
        pacienteDto.setDomicilioDto(domicilioDto);

        return pacienteDto;
    }

    public Paciente mapEntity(PacienteDto pacienteDto) {

        Domicilio domicilio = domicilioMapper.mapEntity(pacienteDto.getDomicilioDto());
        Paciente p = modelMapper.map(pacienteDto, Paciente.class);
        p.setDomicilio(domicilio);
        p.setFechaCreacion(LocalDate.now());

        return p;
    }

    public List<PacienteDto> mapListDto(List<Paciente> pacienteList) {
        List<PacienteDto> pacienteDtoList = pacienteList.stream().map(ode -> mapDTO(ode)).collect(Collectors.toList());
        return pacienteDtoList;
    }
}
