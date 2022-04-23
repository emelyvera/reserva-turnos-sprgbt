package com.cdt.service.impl;

import com.cdt.dto.OdontologoDto;
import com.cdt.entities.Odontologo;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.mapper.OdontologoMapper;
import com.cdt.repository.IOdontologoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceImplTest {

    @Mock
    OdontologoMapper odontologoMapper;

    @Mock
    IOdontologoRepository iOdontologoRepository;

    @InjectMocks
    OdontologoServiceImpl odontologoService;

    OdontologoDto odontologoDto = null;
    Odontologo odontologoEntity = null;


    @BeforeEach
    void setUp() {

        // DTO
        odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Fulano");
        odontologoDto.setMatricula(12345);

        // Entity
        odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pepito");
        odontologoEntity.setApellido("Fulano");
        odontologoEntity.setMatricula(12345);

    }

    @Test
    @DisplayName("registrar odontologo on DB test")
    void registrarOdontologoTest() throws Exception {

        // Given
        Mockito.when(odontologoMapper.mapEntity(odontologoDto)).thenReturn(odontologoEntity);

        Mockito.when(iOdontologoRepository.save(odontologoEntity)).then(invocationOnMock -> {
            odontologoEntity.setId(1);
            return odontologoEntity;
        });

        Mockito.when(odontologoMapper.mapDTO(odontologoEntity)).then(invocationOnMock -> {
            odontologoDto.setId(1);
            return odontologoDto;
        });

        // When
        OdontologoDto odontologoResponseDto = odontologoService.registrar(odontologoDto);

        // Then
        assertNotNull(odontologoResponseDto);
        assertEquals(1, odontologoResponseDto.getId());
        assertEquals("Pepito", odontologoResponseDto.getNombre());
        assertEquals("Fulano", odontologoResponseDto.getApellido());
        assertEquals(12345, odontologoResponseDto.getMatricula());
    }

    @Test
    @DisplayName("modificar odontologo on DB test")
    void modificarOdontologoTest() throws Exception {

        // Given
        Mockito.when(odontologoMapper.mapEntity(odontologoDto)).then(invocationOnMock -> {
            odontologoEntity.setId(1);
            return odontologoEntity;
        });

        Mockito.when(iOdontologoRepository.save(odontologoEntity)).then(invocationOnMock -> {

            odontologoEntity.setId(1);
            odontologoEntity.setNombre("Pepito2");
            odontologoEntity.setApellido("Fulano2");
            odontologoEntity.setMatricula(12345);
            return odontologoEntity;
        });

        Mockito.when(odontologoMapper.mapDTO(odontologoEntity)).then(invocationOnMock -> {
            odontologoDto.setId(1);
            odontologoDto.setNombre("Pepito2");
            odontologoDto.setApellido("Fulano2");
            odontologoDto.setMatricula(12345);
            return odontologoDto;
        });

        // When
        OdontologoDto odontologoResponseDto = odontologoService.modificar(odontologoDto);

        // Then
        assertNotNull(odontologoResponseDto);
        assertEquals(1, odontologoResponseDto.getId());
        assertEquals("Pepito2", odontologoResponseDto.getNombre());
        assertEquals("Fulano2", odontologoResponseDto.getApellido());
        assertEquals(12345, odontologoResponseDto.getMatricula());
    }

    @Test
    @DisplayName("listar odontologo on DB test")
    void listarOdontologoTest() throws Exception {

        // Given
        odontologoEntity.setId(1);
        List<Odontologo> odontologoList = new ArrayList<>();
        odontologoList.add(odontologoEntity);

        Mockito.when(iOdontologoRepository.findAll()).thenReturn(odontologoList);

        Mockito.when(odontologoMapper.mapListDto(odontologoList)).then(invocationOnMock -> {
            odontologoDto.setId(1);
            List<OdontologoDto> odontologoDtoList = new ArrayList<>();
            odontologoDtoList.add(odontologoDto);
            return odontologoDtoList;
        });

        // When
        List<OdontologoDto> odontologoDtoList = odontologoService.listar();

        // Then
        assertNotNull(odontologoDtoList);
        assertEquals(1, odontologoDtoList.size());
        assertEquals(1, odontologoDtoList.get(0).getId());
        assertEquals("Pepito", odontologoDtoList.get(0).getNombre());
    }

    @Test
    @DisplayName("buscar Odontologo por Id on DB test")
    void buscarPorIdOdontologoTest() throws Exception {

        // Given
        Mockito.when(iOdontologoRepository.findById(1)).then(invocationOnMock -> {
            odontologoEntity.setId(1);
            return Optional.of(odontologoEntity);
        });

        Mockito.when(odontologoMapper.mapDTO(odontologoEntity)).then(invocationOnMock -> {
            odontologoDto.setId(1);
            return odontologoDto;
        });

        // When
        OdontologoDto odontologoResponseDto = odontologoService.buscarPorId(1);

        // Then
        assertNotNull(odontologoResponseDto);
        assertEquals(1, odontologoResponseDto.getId());
        assertEquals("Pepito", odontologoResponseDto.getNombre());
        assertEquals("Fulano", odontologoResponseDto.getApellido());
    }

    @Test
    @DisplayName("eliminar odontolog on DB test")
    void eliminarOdontologoTest() throws Exception {

        // Given
        Mockito.when(iOdontologoRepository.findById(1)).then(invocationOnMock -> {
            odontologoEntity.setId(1);
            return Optional.of(odontologoEntity);
        });

        Mockito.doNothing().when(iOdontologoRepository).deleteById(1);

        // When
        odontologoService.eliminar(1);

        Mockito.doThrow(new ModelNotFoundException("Odontologo con id: 1.  No existe en BD.")).when(iOdontologoRepository).findById(1);

        ModelNotFoundException exception = assertThrows(ModelNotFoundException.class, () -> {
            odontologoService.buscarPorId(1);
        });

        // Then
        assertNotNull(exception);
        assertEquals("Odontologo con id: 1.  No existe en BD.", exception.getMessage());
        Mockito.verify(iOdontologoRepository, Mockito.times(2)).findById(1);
    }
}