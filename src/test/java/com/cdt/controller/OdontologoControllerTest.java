package com.cdt.controller;

import com.cdt.dto.OdontologoDto;
import com.cdt.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import java.util.ArrayList;
import java.util.List;

/**
 *  Proximamente se estarán ajustando los controller, ya que aun se esta estudiando como implementarle el token
 *  Sin implementarle spring security funcionan correctamente-
 *
 * @WebMvcTest(OdontologoController.class)
 * class OdontologoControllerTest {
 *
 *     @Autowired
 *     private MockMvc mvc;
 *
 *     @MockBean
 *     private IOdontologoService service;
 *
 *     ObjectMapper objectMapper;
 *
 *     OdontologoDto odontologoDto = null;
 *
 *
 *     @BeforeEach
 *     void setUp() {
 *
 *         objectMapper = new ObjectMapper();
 *         objectMapper.findAndRegisterModules();
 *
 *         // DTO
 *         odontologoDto = new OdontologoDto();
 *         odontologoDto.setNombre("Pepe");
 *         odontologoDto.setApellido("Perez");
 *         odontologoDto.setMatricula(12345);
 *     }
 *
 *     @Test
 *     @DisplayName("registrar odontologo on DB test")
 *     void registrarOdontologoTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(service.registrar(ArgumentMatchers.any())).then(invocationOnMock -> {
 *             OdontologoDto newOdontologoDto = invocationOnMock.getArgument(0);
 *             newOdontologoDto.setId(1);
 *             newOdontologoDto.setNombre("Pepe");
 *             newOdontologoDto.setApellido("Perez");
 *             newOdontologoDto.setMatricula(12345);
 *             return newOdontologoDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.post("/odontologos")
 *                         .content(asJsonString(odontologoDto))
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isCreated())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Pepe"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Perez"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(12345));
 *
 *         Mockito.verify(service).registrar(ArgumentMatchers.any());
 *
 *     }
 *
 *     @Test
 *     @DisplayName("listar odontologo on DB test")
 *     void listarOdontologoTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(service.listar()).then(invocationOnMock -> {
 *             List<OdontologoDto> odontologoDtoList = new ArrayList<>();
 *
 *             OdontologoDto odontologoDto = new OdontologoDto();
 *             odontologoDto.setId(1);
 *             odontologoDto.setNombre("Pepe");
 *             odontologoDto.setApellido("Perez");
 *             odontologoDto.setMatricula(12345);
 *
 *             odontologoDtoList.add(odontologoDto);
 *
 *             return odontologoDtoList;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.get("/odontologos")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Pepe"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].apellido").value("Perez"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].matricula").value(12345));
 *
 *         Mockito.verify(service).listar();
 *     }
 *
 *     @Test
 *     @DisplayName("buscar odontologo por id on DB test")
 *     void buscarPorIdOdontologoTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(service.buscarPorId(1)).then(invocationOnMock -> {
 *             OdontologoDto odontologoDto = new OdontologoDto();
 *             odontologoDto.setId(1);
 *             odontologoDto.setNombre("Pepe");
 *             odontologoDto.setApellido("Perez");
 *             odontologoDto.setMatricula(12345);
 *             return odontologoDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.get("/odontologos/1")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Pepe"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Perez"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(12345));
 *
 *         Mockito.verify(service).buscarPorId(1);
 *     }
 *
 *     @Test
 *     @DisplayName("actualizar odontologo on DB test")
 *     void actualizarOdontologoTest() throws Exception {
 *
 *         // Given
 *         Mockito.when(service.modificar(ArgumentMatchers.any())).then(invocationOnMock -> {
 *             OdontologoDto odontologoNewDto = new OdontologoDto();
 *             odontologoNewDto.setId(1);
 *             odontologoNewDto.setNombre("Pepe2");
 *             odontologoNewDto.setApellido("Perez2");
 *             odontologoNewDto.setMatricula(123452);
 *             return odontologoNewDto;
 *         });
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.put("/odontologos")
 *                         .content(asJsonString(odontologoDto))
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Pepe2"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Perez2"))
 *                 .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(123452));
 *
 *         Mockito.verify(service).modificar(ArgumentMatchers.any());
 *     }
 *
 *     @Test
 *     @DisplayName("eliminar odontologo on DB test")
 *     void eliminarOdontologoTest() throws Exception {
 *
 *         // Given
 *         Mockito.doNothing().when(service).eliminar(1);
 *
 *         // When
 *         mvc.perform(MockMvcRequestBuilders.delete("/odontologos/1")
 *                         .contentType(MediaType.APPLICATION_JSON)
 *                         .accept(MediaType.APPLICATION_JSON)
 *                 )
 *
 *                 // Then
 *                 .andExpect(MockMvcResultMatchers.status().isNoContent());
 *
 *         Mockito.verify(service).eliminar(1);
 *     }
 *
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
