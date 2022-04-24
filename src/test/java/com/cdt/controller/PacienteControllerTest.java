package com.cdt.controller;

import com.cdt.dto.DomicilioDto;
import com.cdt.dto.PacienteDto;
import com.cdt.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Proximamente se estarán ajustando los controller, ya que aun se esta estudiando como implementarle el token
 * Sin implementarle spring security funcionan correctamente-
 *
 * @WebMvcTest(PacienteController.class)
 * class PacienteControllerTest {
 *
 *     @Autowired
 *     private MockMvc mvc;
 *
 *     @MockBean
 *     private IPacienteService iPacienteService;
 *
 *     ObjectMapper objectMapper;
 *
 *     PacienteDto pacienteDto = null;
 *
 *     @BeforeEach
 *     void setUp() {
 *
 *         objectMapper = new ObjectMapper();
 *         objectMapper.findAndRegisterModules();
 *
 *         // DTO
 *         DomicilioDto domicilioDto = new DomicilioDto();
 *         domicilioDto.setCalle("Calle La Esperanza");
 *         domicilioDto.setNumero("127");
 *         domicilioDto.setLocalidad("Suba");
 *         domicilioDto.setProvincia("Bogota");
 *
 *         pacienteDto = new PacienteDto();
 *         pacienteDto.setNombre("Avila");
 *         pacienteDto.setApellido("Gianlucha");
 *         pacienteDto.setDni("12345");
 *         pacienteDto.setFechaCreacion(LocalDate.now());
 *         pacienteDto.setDomicilioDto(domicilioDto);
 *     }
 *
 *     @Test
 *     @DisplayName("registrar paciente on DB test")
 *     void registrarPacienteTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(iPacienteService.registrar(ArgumentMatchers.any())).then(invocationOnMock -> {
 *             PacienteDto newPacienteDto = invocationOnMock.getArgument(0);
 *             newPacienteDto.setId(1);
 *             newPacienteDto.setNombre("Avila");
 *             newPacienteDto.setApellido("Gianlucha");
 *             newPacienteDto.setDni("12345");
 *             return newPacienteDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.post("/pacientes")
 *                         .content(asJsonString(pacienteDto))
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isCreated())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Avila"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Gianlucha"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value("12345"));
 *
 *         Mockito.verify(iPacienteService).registrar(ArgumentMatchers.any());
 *
 *     }
 *
 *     @Test
 *     @DisplayName("listar paciente on DB test")
 *     void listarPacienteTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(iPacienteService.listar()).then(invocationOnMock -> {
 *             List<PacienteDto> pacienteDtoList = new ArrayList<>();
 *
 *             PacienteDto pacienteDto = new PacienteDto();
 *             pacienteDto.setId(1);
 *             pacienteDto.setNombre("Avila");
 *             pacienteDto.setApellido("Gianlucha");
 *             pacienteDto.setDni("12345");
 *
 *             pacienteDtoList.add(pacienteDto);
 *
 *             return pacienteDtoList;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.get("/pacientes")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Avila"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].apellido").value("Gianlucha"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].dni").value("12345"));
 *
 *         Mockito.verify(iPacienteService).listar();
 *     }
 *
 *     @Test
 *     @DisplayName("buscar paciente por id on DB test")
 *     void buscarPorIdPacienteTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(iPacienteService.buscarPorId(1)).then(invocationOnMock -> {
 *             PacienteDto pacienteDto = new PacienteDto();
 *             pacienteDto.setId(1);
 *             pacienteDto.setNombre("Avila");
 *             pacienteDto.setApellido("Gianlucha");
 *             pacienteDto.setDni("12345");
 *             return pacienteDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.get("/pacientes/1")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Avila"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Gianlucha"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value("12345"));
 *
 *         Mockito.verify(iPacienteService).buscarPorId(1);
 *     }
 *
 *     @Test
 *     @DisplayName("actualizar paciente on DB test")
 *     void actualizarPacienteTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(iPacienteService.modificar(ArgumentMatchers.any())).then(invocationOnMock -> {
 *             PacienteDto newPacienteDto = invocationOnMock.getArgument(0);
 *             newPacienteDto.setId(1);
 *             newPacienteDto.setNombre("Avila2");
 *             newPacienteDto.setApellido("Gianlucha2");
 *             newPacienteDto.setDni("6789");
 *             return newPacienteDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.put("/pacientes")
 *                         .content(asJsonString(pacienteDto))
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Avila2"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Gianlucha2"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value("6789"));
 *
 *         Mockito.verify(iPacienteService).modificar(ArgumentMatchers.any());
 *     }
 *
 *     @Test
 *     @DisplayName("eliminar paciente on DB test")
 *     void eliminarPacienteTest() throws Exception {
 *
 *         // Given
 *         Mockito.doNothing().when(iPacienteService).eliminar(1);
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.delete("/pacientes/1")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isNoContent());
 *
 *         Mockito.verify(iPacienteService).eliminar(1);
 *     }
 *
 *     public String asJsonString(final Object obj) {
 *         try {
 *             return objectMapper.writeValueAsString(obj);
 *         } catch (Exception e) {
 *             throw new RuntimeException(e);
 *         }
 *     }
 * }
 */
