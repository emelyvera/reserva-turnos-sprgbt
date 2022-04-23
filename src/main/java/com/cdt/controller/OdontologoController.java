package com.cdt.controller;

import com.cdt.dto.OdontologoDto;
import com.cdt.exception.ModelNotFoundException;
import com.cdt.service.IOdontologoService;
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
 * OdontologoController.
 * This class is responsible for managing odontologos.
 *
 * @author Emely Daniela Vera Villamizar, emelydaniivera@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

  /**
   * Service Interface instance.
   */
  @Autowired
  IOdontologoService service;


  /**
   * registrar.
   *
   * @param odontologoRequestDto
   * @return odontologoResponseDto
   * @throws Exception
   */
  @PostMapping()
  public ResponseEntity<OdontologoDto> registrar(
      @Valid @RequestBody OdontologoDto odontologoRequestDto) throws Exception {

    log.info("Odontologo RequestDto: {}", odontologoRequestDto);

    // Execute service method
    OdontologoDto odontologoResponseDto = service.registrar(odontologoRequestDto);

    log.info("Odontologo ResponseDto: {}", odontologoResponseDto);

    return new ResponseEntity<OdontologoDto>(odontologoResponseDto, HttpStatus.CREATED);
  }

  /**
   * listar.
   *
   * @return listOdontologoDto
   * @throws Exception
   */
  @GetMapping()
  public ResponseEntity<List<OdontologoDto>> listar() throws Exception {

    // Execute service method
    List<OdontologoDto> listOdontologoDto = service.listar();

    return new ResponseEntity<List<OdontologoDto>>(listOdontologoDto, HttpStatus.OK);

  }

  /**
   * buscarPorId.
   *
   * @param id
   * @return OdontologoDto
   * @throws Exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<OdontologoDto> listarPorId(@PathVariable Integer id) throws Exception {

    // Execute service method
    OdontologoDto odontologoDto = service.buscarPorId(id);

    // Check Entity
    if (odontologoDto == null) {
      log.error("Odontologo con id: {}.  No existe en BD.", id);
      throw new ModelNotFoundException("Odontologo con id: " + id + ".  No existe en BD.");
    }

    return new ResponseEntity<OdontologoDto>(odontologoDto, HttpStatus.OK);

  }

  /**
   * actualizar.
   *
   * @param odontologoRequestDto
   * @return updatedOdontologoDto
   * @throws Exception
   */
  @PutMapping()
  public ResponseEntity<OdontologoDto> actualizar(
      @Valid @RequestBody OdontologoDto odontologoRequestDto) throws Exception {

    log.info("Odontologo RequestDto: {}", odontologoRequestDto);

    // Execute service method
    OdontologoDto updatedOdontologoDto = service.modificar(odontologoRequestDto);

    log.info("Odontologo ResponseDto: {}", updatedOdontologoDto);

    return new ResponseEntity<OdontologoDto>(updatedOdontologoDto, HttpStatus.OK);
  }

  /**
   * eliminar.
   *
   * @param id
   * @return Void
   * @throws Exception
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {

    // Execute service method
    service.eliminar(id);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

}
