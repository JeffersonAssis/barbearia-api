package com.dio.barbearia.service.interfac;

import java.util.List;

import com.dio.barbearia.dto.ClienteDto;
import com.dio.barbearia.dto.ClienteDtoInsert;

public interface IClienteService {
  
  ClienteDto findById(Long id);
  ClienteDto save(ClienteDtoInsert cliente);
  List<ClienteDto> findAll();
  void delete(Long id);
  ClienteDto update(ClienteDto cliente);
  void existsByEmail(String email);
  void existsByTelefone(String telefone);
  ClienteDto findByEmail(String email);
  ClienteDto findByTelefone(String telefone);


}
