package com.dio.barbearia.controller;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneId;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dio.barbearia.dto.AgendaDtoInsert;
import com.dio.barbearia.dto.AgendaResponseDto;
import com.dio.barbearia.service.AgendaService;
import com.dio.barbearia.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("agenda")
@CrossOrigin(origins = "*")
public class AgendaController {

  @Autowired
  private AgendaService agendaService;

  @PostMapping
  public ResponseEntity<?> save(@RequestBody @Valid AgendaDtoInsert agenda, BindingResult bindingResult) {
    ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
    if(validadorBindingResult.hasErrors()){
      return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
    }
    return ResponseEntity.ok(agendaService.save(agenda));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    agendaService.delete(id);
    return ResponseEntity.status(Response.SC_NO_CONTENT).build();
  }

@GetMapping("{ano}/{mes}")
public ResponseEntity<?> buscarData(@PathVariable Integer ano, @PathVariable Integer mes) {
    ZoneId zoneId = ZoneId.of("America/Sao_Paulo"); 
    YearMonth mesAno = YearMonth.of(ano, mes);

    OffsetDateTime inicio = mesAno.atDay(1)
            .atStartOfDay(zoneId)
            .toOffsetDateTime();

    OffsetDateTime fim = mesAno.atEndOfMonth()
            .atTime(23, 59, 59, 999999999)
            .atZone(zoneId)
            .toOffsetDateTime();

      var agenda = agendaService.buscarData(inicio, fim);
    return ResponseEntity.status(HttpStatus.OK).body(new AgendaResponseDto(ano, mes, agenda));
  }

}
