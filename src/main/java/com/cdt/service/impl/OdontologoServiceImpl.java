package com.cdt.service.impl;

import com.cdt.dto.OdontologoDto;
import com.cdt.entities.Odontologo;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.mapper.OdontologoMapper;
import com.cdt.repository.IOdontologoRepository;
import com.cdt.service.IOdontologoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OdontologoServiceImpl.
 * This class is responsible for implementing the contract to managing odontologos.
 *
 * @author Emely Daniela Vera Villamizar, emelydaniivera@gmail.com
 */
@Slf4j
@Service
public class OdontologoServiceImpl implements IOdontologoService {

    /**
     * Repository Interface instance.
     */
    @Autowired
    IOdontologoRepository repository;

    @Autowired
    OdontologoMapper mapper;


    @Override
    public OdontologoDto registrar(OdontologoDto odontologoDto) throws Exception {

        Odontologo obj = mapper.mapEntity(odontologoDto);
        Odontologo entity = repository.save(obj);
        OdontologoDto dto = mapper.mapDTO(entity);
        return dto;

    }

    @Override
    public OdontologoDto modificar(OdontologoDto odontologoDto) throws Exception {

        Odontologo o = mapper.mapEntity(odontologoDto);
        Odontologo entity = repository.save(o);
        OdontologoDto dto = mapper.mapDTO(entity);
        return dto;

    }

    @Override
    public List<OdontologoDto> listar() throws Exception {

        List<Odontologo> odontologosList = repository.findAll();
        List<OdontologoDto> odontologoDtoList = mapper.mapListDto(odontologosList);
        return odontologoDtoList;

    }

    @Override
    public OdontologoDto buscarPorId(Integer id) throws Exception {

        Odontologo odontologoEntity = repository.findById(id).orElse(null);

        if (odontologoEntity == null) {
            log.error("Odontologo con id: {}.  No existe en BD. ", id);
            throw new ModelNotFoundException("Odontologo con id: " + id + ".  No existe en BD.");

        }

        OdontologoDto dto = mapper.mapDTO(odontologoEntity);

        return dto;
    }

    @Override
    public void eliminar(Integer id) throws Exception {

        buscarPorId(id);
        repository.deleteById(id);

    }
}
