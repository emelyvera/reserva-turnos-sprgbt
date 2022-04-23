package com.cdt.service.impl;

import com.cdt.dto.PacienteDto;
import com.cdt.entities.Paciente;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.mapper.PacienteMapper;
import com.cdt.repository.IPacienteRepository;
import com.cdt.service.IPacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    IPacienteRepository iPacienteRepo;

    @Autowired
    PacienteMapper pacienteMapper;


    @Override
    public PacienteDto registrar(PacienteDto pacienteDto) throws Exception {

        Paciente p = pacienteMapper.mapEntity(pacienteDto);
        Paciente entity = iPacienteRepo.save(p);
        PacienteDto dto = pacienteMapper.mapDTO(entity);
        return dto;

    }

    @Override
    public PacienteDto modificar(PacienteDto pacienteDto) throws Exception {

        Paciente p = pacienteMapper.mapEntity(pacienteDto);
        Paciente entity = iPacienteRepo.save(p);
        PacienteDto dto = pacienteMapper.mapDTO(entity);
        return dto;
    }

    @Override
    public List<PacienteDto> listar() throws Exception {

        List<Paciente> pacienteList = iPacienteRepo.findAll();
        List<PacienteDto> pacienteDtoList = pacienteMapper.mapListDto(pacienteList);
        return pacienteDtoList;
    }

    @Override
    public PacienteDto buscarPorId(Integer id) throws Exception {

        Paciente pacienteEntity = iPacienteRepo.findById(id).orElse(null);

        if (pacienteEntity == null) {
            log.error("Paciente con id: {}.  No existe en BD.", id);
            throw new ModelNotFoundException("Paciente con id: " + id + ".  No existe en BD.");

        }

        PacienteDto dto = pacienteMapper.mapDTO(pacienteEntity);

        return dto;
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        buscarPorId(id);
        iPacienteRepo.deleteById(id);
    }
}
