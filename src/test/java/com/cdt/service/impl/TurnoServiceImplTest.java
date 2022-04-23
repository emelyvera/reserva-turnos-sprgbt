package com.cdt.service.impl;

import com.cdt.dto.DomicilioDto;
import com.cdt.dto.OdontologoDto;
import com.cdt.dto.PacienteDto;
import com.cdt.dto.TurnoDto;
import com.cdt.entities.Domicilio;
import com.cdt.entities.Odontologo;
import com.cdt.entities.Paciente;
import com.cdt.entities.Turno;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.mapper.TurnoMapper;
import com.cdt.repository.ITurnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceImplTest {

    @Mock
    TurnoMapper turnoMapper;

    @Mock
    ITurnoRepository iTurnoRepository;

    @InjectMocks
    TurnoServiceImpl turnoService;

    TurnoDto turnoDto = null;
    Turno turnoEntity = null;

    @BeforeEach
    void setUp() {

        //DTO
        OdontologoDto odontologoDto = new OdontologoDto();
        odontologoDto.setNombre("Pepito");
        odontologoDto.setApellido("Fulano");
        odontologoDto.setMatricula(12345);

        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle");
        domicilioDto.setNumero("1234");
        domicilioDto.setLocalidad("Suba");
        domicilioDto.setProvincia("Bogota");

        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setNombre("Avila");
        pacienteDto.setApellido("Gianlucha");
        pacienteDto.setDni("12345");
        pacienteDto.setFechaCreacion(LocalDate.now());
        pacienteDto.setDomicilioDto(domicilioDto);

        turnoDto = new TurnoDto();
        turnoDto.setOdontologoDto(odontologoDto);
        turnoDto.setPacienteDto(pacienteDto);
        turnoDto.setFechaHora(LocalDateTime.now());

        // Entity
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pepito");
        odontologoEntity.setApellido("Fulano");
        odontologoEntity.setMatricula(12345);

        Domicilio domicilioEntity = new Domicilio();
        domicilioEntity.setCalle("Calle La Esperanza");
        domicilioEntity.setNumero("127");
        domicilioEntity.setLocalidad("Suba");
        domicilioEntity.setProvincia("Bogota");

        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setNombre("Avila");
        pacienteEntity.setApellido("Gianlucha");
        pacienteEntity.setDni("12345");
        pacienteEntity.setFechaCreacion(LocalDate.now());
        pacienteEntity.setDomicilio(domicilioEntity);

        turnoEntity = new Turno();
        turnoEntity.setOdontologo(odontologoEntity);
        turnoEntity.setPaciente(pacienteEntity);
        turnoEntity.setFechaHora(LocalDateTime.now());

    }


    @Test
    @DisplayName("registrar turno on DB test")
    void registrarTurnoTest() throws Exception {

        //Given
        Mockito.when(turnoMapper.mapEntity(turnoDto)).thenReturn(turnoEntity);

        Mockito.when(iTurnoRepository.save(turnoEntity)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return turnoEntity;
        });

        Mockito.when(turnoMapper.mapDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.registrar(turnoDto);

        //Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Pepito", turnoResponseDto.getOdontologoDto().getNombre());
        assertEquals("Avila", turnoResponseDto.getPacienteDto().getNombre());

    }

    @Test
    @DisplayName("modificar Turno on DB test")
    void modificarTurnoTest() throws Exception {

        // Given
        Mockito.when(turnoMapper.mapEntity(turnoDto)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return turnoEntity;
        });

        Mockito.when(iTurnoRepository.save(turnoEntity)).then(invocationOnMock -> {

            turnoEntity.setId(1);
            turnoEntity.setOdontologo(turnoEntity.getOdontologo());
            turnoEntity.setPaciente(turnoEntity.getPaciente());

            return turnoEntity;
        });

        Mockito.when(turnoMapper.mapDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            turnoDto.setOdontologoDto(turnoDto.getOdontologoDto());
            turnoDto.setPacienteDto(turnoDto.getPacienteDto());
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.modificar(turnoDto);

        // Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Pepito", turnoResponseDto.getOdontologoDto().getNombre());

    }

    @Test
    @DisplayName("listar turno on DB test")
    void listarTurnoTest() throws Exception {

        // Given
        turnoEntity.setId(1);
        List<Turno> turnoList = new ArrayList<>();
        turnoList.add(turnoEntity);

        Mockito.when(iTurnoRepository.findAll()).thenReturn(turnoList);

        Mockito.when(turnoMapper.mapListDto(turnoList)).then(invocationOnMock -> {
            turnoDto.setId(1);
            List<TurnoDto> turnoDtoList = new ArrayList<>();
            turnoDtoList.add(turnoDto);
            return turnoDtoList;
        });

        // When
        List<TurnoDto> turnoDtoList = turnoService.listar();

        // Then
        assertNotNull(turnoDtoList);
        assertEquals(1, turnoDtoList.size());
        assertEquals(1, turnoDtoList.get(0).getId());
        assertEquals("Fulano", turnoDtoList.get(0).getOdontologoDto().getApellido());
        assertEquals("Gianlucha", turnoDtoList.get(0).getPacienteDto().getApellido());
    }

    @Test
    @DisplayName("buscar turnos por Id on DB test")
    void buscarPorIdTurnosTest() throws Exception {

        // Given
        Mockito.when(iTurnoRepository.findById(1)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return Optional.of(turnoEntity);
        });

        Mockito.when(turnoMapper.mapDto(turnoEntity)).then(invocationOnMock -> {
            turnoDto.setId(1);
            return turnoDto;
        });

        // When
        TurnoDto turnoResponseDto = turnoService.buscarPorId(1);

        // Then
        assertNotNull(turnoResponseDto);
        assertEquals(1, turnoResponseDto.getId());
        assertEquals("Fulano", turnoResponseDto.getOdontologoDto().getApellido());
        assertEquals("Gianlucha", turnoResponseDto.getPacienteDto().getApellido());

    }

    @Test
    @DisplayName("eliminar turnos on DB test")
    void eliminarTurnosTest() throws Exception {

        // Given
        Mockito.when(iTurnoRepository.findById(1)).then(invocationOnMock -> {
            turnoEntity.setId(1);
            return Optional.of(turnoEntity);
        });

        Mockito.doNothing().when(iTurnoRepository).deleteById(1);

        // When
        turnoService.eliminar(1);

        Mockito.doThrow(new ModelNotFoundException("Turno con id: 1.  No existe en BD.")).when(iTurnoRepository).findById(1);

        ModelNotFoundException exception = assertThrows(ModelNotFoundException.class, () -> {
            turnoService.buscarPorId(1);
        });

        // Then
        assertNotNull(exception);
        assertEquals("Turno con id: 1.  No existe en BD.", exception.getMessage());
        Mockito.verify(iTurnoRepository, Mockito.times(2)).findById(1);
    }

}