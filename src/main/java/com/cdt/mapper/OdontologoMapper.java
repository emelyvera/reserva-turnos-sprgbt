package com.cdt.mapper;

import com.cdt.dto.OdontologoDto;
import com.cdt.entities.Odontologo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OdontologoMapper.
 * This class is responsible for converting DTO to Entity and vice versa.
 *
 * @author Emely Daniela Vera Villamizar, emelydaniivera@gmail.com
 */
@Slf4j
@Component
public class OdontologoMapper {

    /**
     * ModelMapper instance.
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * mapDTO.
     *
     * @param odontologoEntity
     * @return OdontologoDto
     */
    public OdontologoDto mapDTO(Odontologo odontologoEntity) {
        log.info("*** Odontologo Entity: {}", odontologoEntity);
        OdontologoDto odontologoDTO = modelMapper.map(odontologoEntity, OdontologoDto.class);
        log.info("*** OdontologoDto: {}", odontologoDTO);

        return odontologoDTO;
    }

    /**
     * mapEntity
     *
     * @param odontologoDTO
     * @return odontologoEntity
     */
    public Odontologo mapEntity(OdontologoDto odontologoDTO) {

        Odontologo odontologoEntity = modelMapper.map(odontologoDTO, Odontologo.class);
        log.info("*** Odontologo Entity: {}", odontologoEntity);

        return odontologoEntity;
    }

    /**
     * mapListDto.
     *
     * @param listOdontologoEntity
     * @return listOdontologoDTO
     */
    public List<OdontologoDto> mapListDto(List<Odontologo> listOdontologoEntity) {

        List<OdontologoDto> listOdontologoDTO = listOdontologoEntity.stream()
                .map(odontologoEntity -> mapDTO(odontologoEntity))
                .collect(Collectors.toList());
        log.info("List<OdontologoDTO>: {}", listOdontologoDTO);

        return listOdontologoDTO;
    }

}
