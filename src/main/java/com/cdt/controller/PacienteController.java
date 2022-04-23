package com.cdt.controller;

import com.cdt.dto.PacienteDto;
import com.cdt.service.IPacienteService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PacienteController
 * This class is responsible for managing patients.
 *
 * @author Emely Daniela Vera Villamizar, emelydaniivera@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  /**
   * Service Interface instance.
   */
  @Autowired
  IPacienteService iPacienteService;


  /**
   * registrar
   *
   * @param pacienteDto
   * @return pacienteResponseDto
   * @throws Exception
   */
  @PostMapping()
  public ResponseEntity<PacienteDto> registrar(@Valid @RequestBody PacienteDto pacienteDto)
      throws Exception {

    log.info("Request Body: {} ", pacienteDto);

    // Execute service method
    PacienteDto pacienteResponseDto = iPacienteService.registrar(pacienteDto);

    log.info("Response Body: {} ", pacienteResponseDto);

    return new ResponseEntity<PacienteDto>(pacienteResponseDto, HttpStatus.CREATED);
  }

  /**
   * listar
   *
   * @return pacienteDtoList
   * @throws Exception
   */
  @GetMapping()
  public ResponseEntity<List<PacienteDto>> listar() throws Exception {

    // Execute service method
    List<PacienteDto> pacienteDtoList = iPacienteService.listar();

    return new ResponseEntity<List<PacienteDto>>(pacienteDtoList, HttpStatus.OK);

  }

  /**
   * buscarPorId
   *
   * @param id
   * @return dto
   * @throws Exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<PacienteDto> listarPorId(@PathVariable Integer id) throws Exception {

    PacienteDto dto = iPacienteService.buscarPorId(id);

    return new ResponseEntity<PacienteDto>(dto, HttpStatus.OK);

  }


  /**
   * actualizar
   *
   * @param pacienteDto
   * @return dto
   * @throws Exception
   */
  @PutMapping()
  public ResponseEntity<PacienteDto> actualizar(@Valid @RequestBody PacienteDto pacienteDto)
      throws Exception {

    PacienteDto dto = iPacienteService.modificar(pacienteDto);

    log.info("Paciente ResponseDto: {}", dto);

    return new ResponseEntity<PacienteDto>(dto, HttpStatus.OK);

  }

  /**
   * eliminar
   *
   * @param id
   * @return Void
   * @throws Exception
   */

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {

    iPacienteService.eliminar(id);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
  
}
