package com.dio.barbearia.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.barbearia.dto.ClienteDto;
import com.dio.barbearia.dto.ClienteDtoInsert;
import com.dio.barbearia.service.ClienteService;
import com.dio.barbearia.util.ValidadorBindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;
  
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    return ResponseEntity.ok(clienteService.findById(id));
  }
  
  @GetMapping
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(clienteService.findAll());
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    clienteService.delete(id);
    return ResponseEntity.status(Response.SC_NO_CONTENT).build();
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody @Valid ClienteDtoInsert cliente, BindingResult bindingResult) {
    ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
    if(validadorBindingResult.hasErrors()){
      return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
    }
    return ResponseEntity.ok(clienteService.save(cliente));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
    ValidadorBindingResult validadorBindingResult = new ValidadorBindingResult(bindingResult);
    if(validadorBindingResult.hasErrors()){
      return ResponseEntity.badRequest().body(validadorBindingResult.getErrors());
    }
    clienteService.findById(id);
    cliente.setId(id);
    return ResponseEntity.ok(clienteService.update(cliente));
  }

}
