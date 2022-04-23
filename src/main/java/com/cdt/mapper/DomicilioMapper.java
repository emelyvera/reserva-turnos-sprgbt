package com.cdt.mapper;

import com.cdt.dto.DomicilioDto;
import com.cdt.entities.Domicilio;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DomicilioMapper {

    @Autowired
    ModelMapper modelMapper;

    public DomicilioDto mapDTO(Domicilio d) {

        DomicilioDto domicilioDto = modelMapper.map(d, DomicilioDto.class);
        return domicilioDto;
    }

    public Domicilio mapEntity(DomicilioDto domicilioDto) {
        log.info("DomicilioDto: {}", domicilioDto);
        Domicilio d = modelMapper.map(domicilioDto, Domicilio.class);
        return d;
    }

    public List<DomicilioDto> mapListDto(List<Domicilio> domicilioList){
        List<DomicilioDto> domicilioDtoList= domicilioList.stream().map(ode-> mapDTO(ode)).collect(Collectors.toList());
        return  domicilioDtoList;
    }
}
