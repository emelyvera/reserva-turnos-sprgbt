package com.cdt.mapper;

import com.cdt.dto.OdontologoDto;
import com.cdt.dto.PacienteDto;
import com.cdt.dto.TurnoDto;
import com.cdt.entities.Odontologo;
import com.cdt.entities.Paciente;
import com.cdt.entities.Turno;
import com.cdt.service.IOdontologoService;
import com.cdt.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TurnoMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IOdontologoService iOdontologoService;

    @Autowired
    IPacienteService iPacienteService;

    @Autowired
    PacienteMapper pacienteMapper;

    @Autowired
    OdontologoMapper odontologoMapper;

    public Turno mapEntity(TurnoDto turnoDto) throws Exception {

        Turno turnoEntity = modelMapper.map(turnoDto, Turno.class);
        turnoEntity.setOdontologo(getOdontologoEntity(turnoDto.getOdontologoDto().getId()));
        turnoEntity.setPaciente(getPacienteEntity(turnoDto.getPacienteDto().getId()));

        return turnoEntity;
    }

    public TurnoDto mapDto(Turno turno)  {

        OdontologoDto odontologoDto = odontologoMapper.mapDTO(turno.getOdontologo());
        PacienteDto pacienteDto = pacienteMapper.mapDTO(turno.getPaciente());
        TurnoDto turnoDto = modelMapper.map(turno, TurnoDto.class);
        turnoDto.setOdontologoDto(odontologoDto);
        turnoDto.setPacienteDto(pacienteDto);

        return turnoDto;

    }

    public List<TurnoDto> mapListDto(List<Turno> turnoList)  {
        List<TurnoDto> turnoDtoList = turnoList.stream().map(ode -> mapDto(ode)).collect(Collectors.toList());
        return turnoDtoList;
    }

    private Odontologo getOdontologoEntity(Integer id) throws Exception {

        OdontologoDto odontologoDto= iOdontologoService.buscarPorId(id);
        Odontologo odontologoEntity = odontologoMapper.mapEntity(odontologoDto);

        return odontologoEntity;

    }

    private Paciente getPacienteEntity(Integer id) throws Exception {

        PacienteDto pacienteDto = iPacienteService.buscarPorId(id);
        Paciente pacienteEntity = pacienteMapper.mapEntity(pacienteDto);

        return pacienteEntity;

    }


}
