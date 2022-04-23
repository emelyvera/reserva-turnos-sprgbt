package com.cdt.controller;

import com.cdt.dto.TurnoDto;
import com.cdt.service.ITurnoService;
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

@Slf4j
@RestController
@RequestMapping("/turnos")
public class TurnoController {

  @Autowired
  ITurnoService iTurnoService;

  @PostMapping()
  public ResponseEntity<TurnoDto> registrar(@Valid @RequestBody TurnoDto turnoDto)
      throws Exception {

    log.info("Request Body: {} ", turnoDto);

    // Execute service method
    TurnoDto turnoResponseDto = iTurnoService.registrar(turnoDto);

    log.info("Response Body: {} ", turnoResponseDto);

    return new ResponseEntity<TurnoDto>(turnoResponseDto, HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<List<TurnoDto>> listar() throws Exception {

    List<TurnoDto> turnoDtoList = iTurnoService.listar();

    return new ResponseEntity<List<TurnoDto>>(turnoDtoList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TurnoDto> listarPorId(@PathVariable Integer id) throws Exception {

    TurnoDto dto = iTurnoService.buscarPorId(id);

    return new ResponseEntity<TurnoDto>(dto, HttpStatus.OK);

  }

  @PutMapping()
  public ResponseEntity<TurnoDto> actualizar(@Valid @RequestBody TurnoDto turnoDto)
      throws Exception {

    TurnoDto dto = iTurnoService.modificar(turnoDto);

    log.info("Turno ResponseDto: {}", dto);

    return new ResponseEntity<TurnoDto>(dto, HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {

    iTurnoService.eliminar(id);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
  
}
