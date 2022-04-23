package com.cdt.integrationjpatest;

import com.cdt.entities.Domicilio;
import com.cdt.entities.Odontologo;
import com.cdt.entities.Paciente;
import com.cdt.entities.Turno;
import com.cdt.repository.IOdontologoRepository;
import com.cdt.repository.IPacienteRepository;
import com.cdt.repository.ITurnoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TurnoJpaTest {

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    ITurnoRepository turnoRepository;

    Turno newTurnoEntity = null;

    @BeforeEach
    void setUp() {

        // Given - Odontologo
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre("Pepito");
        odontologoEntity.setApellido("Fulano");
        odontologoEntity.setMatricula(12345);

        //When - Odontologo
        Odontologo newOdontologoEntity = odontologoRepository.save(odontologoEntity);

        // Given - Paciente
        Domicilio domicilioEntity = new Domicilio();
        domicilioEntity.setCalle("Calle");
        domicilioEntity.setNumero("1234");
        domicilioEntity.setLocalidad("Suba");
        domicilioEntity.setProvincia("Bogota");

        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setNombre("Pepe");
        pacienteEntity.setApellido("Florez");
        pacienteEntity.setDni("12345");
        pacienteEntity.setFechaCreacion(LocalDate.now());
        pacienteEntity.setDomicilio(domicilioEntity);

        //When - Paciente
        Paciente newPacienteEntity = pacienteRepository.save(pacienteEntity);

        // Given - Turno
        Turno turnoEntity = new Turno();
        turnoEntity.setOdontologo(odontologoEntity);
        turnoEntity.setPaciente(pacienteEntity);
        turnoEntity.setFechaHora(LocalDateTime.now());

        // When - Turno
        newTurnoEntity = turnoRepository.save(turnoEntity);

    }

    @Test
    @DisplayName("save turno on DB test")
    void saveTurnoTest() {

        // Given

        // When

        //Then
        assertNotNull(newTurnoEntity);
        assertEquals("Pepito", newTurnoEntity.getOdontologo().getNombre());
        assertEquals(12345, newTurnoEntity.getOdontologo().getMatricula());
        assertEquals("Pepe", newTurnoEntity.getPaciente().getNombre());
    }

    @Test
    @DisplayName("find all turno from DB test")
    void findAllTurnoTest() {

        // Given

        // When
        List<Turno> listTurnos = turnoRepository.findAll();

        // Then
        Assertions.assertThat(listTurnos).hasSize(1);
        assertNotNull(listTurnos.get(0));
        assertEquals("Pepito", listTurnos.get(0).getOdontologo().getNombre());
        assertEquals(12345, listTurnos.get(0).getOdontologo().getMatricula());
        assertEquals("Pepe", listTurnos.get(0).getPaciente().getNombre());
    }

    @Test
    @DisplayName("find turno by id from DB test")
    void findByIdPacienteTest() {

        // Given

        // When
        Optional<Turno> turnoEntity = turnoRepository.findById(newTurnoEntity.getId());

        // Then
        assertTrue(turnoEntity.isPresent());
        assertEquals("Pepito", turnoEntity.orElseThrow().getOdontologo().getNombre());
        assertEquals(12345, turnoEntity.orElseThrow().getOdontologo().getMatricula());
        assertEquals("Pepe", turnoEntity.orElseThrow().getPaciente().getNombre());
    }

    @Test
    @DisplayName("update existing pacient on DB test")
    void updatePacientTest() {

        // Given
        LocalDateTime updatedLocalDateTime = LocalDateTime.now();
        newTurnoEntity.setFechaHora(updatedLocalDateTime);

        //When
        Turno updatedTurnoEntity = turnoRepository.save(newTurnoEntity);

        //Then
        assertNotNull(updatedTurnoEntity);
        assertEquals("Pepito", updatedTurnoEntity.getOdontologo().getNombre());
        assertEquals(12345, updatedTurnoEntity.getOdontologo().getMatricula());
        assertEquals("Pepe", updatedTurnoEntity.getPaciente().getNombre());
        assertEquals(updatedLocalDateTime, updatedTurnoEntity.getFechaHora());
    }

    @Test
    @DisplayName("delete existing turno on DB test")
    void deleteTurnoTest() {

        // Given

        // When
        turnoRepository.delete(newTurnoEntity);

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            turnoRepository.findById(newTurnoEntity.getId()).orElseThrow();
        });
    }

}
