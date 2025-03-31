package com.dio.barbearia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.barbearia.dto.ClienteDto;
import com.dio.barbearia.dto.ClienteDtoInsert;
import com.dio.barbearia.exception.ObjectNotFoundException;
import com.dio.barbearia.model.Cliente;
import com.dio.barbearia.repository.ClienteRepository;
import com.dio.barbearia.service.interfac.IClienteService;

@Service
public class ClienteService implements IClienteService {
 
  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public ClienteDto findById(Long id) {
   var c =  clienteRepository.findById(id);
   if(c.isPresent()) {
     return c.get().clienteToDto();
   }
   throw new ObjectNotFoundException("Cliente não encontrado");
  }

  @Override
  public ClienteDto save(ClienteDtoInsert cliente) {
    existsByEmail(cliente.getEmail());
    existsByTelefone(cliente.getTelefone());
    return clienteRepository.save(cliente.dtoToCliente()).clienteToDto();
  }

  @Override
  public List<ClienteDto> findAll() {
    return clienteRepository.findAll().stream().map(Cliente::clienteToDto).collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    var c = findById(id);
    clienteRepository.delete(c.dtoToCliente());
   
  }

  @Override
  public ClienteDto update(ClienteDto cliente) {
    var c = findById(cliente.getId());
    c.setNome(cliente.getNome());
    c.setTelefone(cliente.getTelefone());
    c.setEmail(cliente.getEmail());
    return clienteRepository.save(c.dtoToCliente()).clienteToDto();
  }

  @Override
  public void existsByEmail(String email) {
    if(clienteRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("Já existe um cliente cadastrado com esse email");
    }
  }

  @Override
  public void existsByTelefone(String telefone) {
    if(clienteRepository.existsByTelefone(telefone)) {
      throw new IllegalArgumentException("Já existe um cliente cadastrado com esse telefone");
    }
  }

  @Override
  public ClienteDto findByEmail(String email) {
    var c = clienteRepository.findByEmail(email);
    if(c.isPresent()) {
      return c.get().clienteToDto();
    }
    throw new ObjectNotFoundException("Cliente não encontrado");
  }

  @Override
  public ClienteDto findByTelefone(String telefone) {
    var c = clienteRepository.findByTelefone(telefone);
    if(c.isPresent()) {
      return c.get().clienteToDto();
    }
    throw new ObjectNotFoundException("Cliente não encontrado");
  }

}
