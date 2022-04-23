package com.cdt.service.impl;

import com.cdt.dto.TurnoDto;
import com.cdt.entities.Turno;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.mapper.TurnoMapper;
import com.cdt.repository.ITurnoRepository;
import com.cdt.service.ITurnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    ITurnoRepository iTurnoRepository;

    @Autowired
    TurnoMapper turnoMapper;

    @Override
    public TurnoDto registrar(TurnoDto turnoDto) throws Exception {

        Turno t = turnoMapper.mapEntity(turnoDto);
        Turno entity = iTurnoRepository.save(t);
        TurnoDto dto = turnoMapper.mapDto(entity);
        return dto;

    }

    @Override
    public TurnoDto modificar(TurnoDto turnoDto) throws Exception {

        Turno t= turnoMapper.mapEntity(turnoDto);
        Turno entity= iTurnoRepository.save(t);
        TurnoDto dto = turnoMapper.mapDto(entity);
        return dto;
    }

    @Override
    public List<TurnoDto> listar() throws Exception {

        List<Turno> turnoList= iTurnoRepository.findAll();
        List<TurnoDto> turnoDtoList = turnoMapper.mapListDto(turnoList);
        return turnoDtoList;
    }

    @Override
    public TurnoDto buscarPorId(Integer id) throws Exception {

        Turno turnoEntity = iTurnoRepository.findById(id).orElse(null);

        if (turnoEntity == null) {
            log.error("Turno con id: {}.  No existe en BD.", id);
            throw new ModelNotFoundException("Turno con id: " + id + ".  No existe en BD.");
        }

        TurnoDto dto = turnoMapper.mapDto(turnoEntity);

        return dto;
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        buscarPorId(id);
        iTurnoRepository.deleteById(id);

    }
}
