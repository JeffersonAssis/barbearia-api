package com.dio.barbearia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.barbearia.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  boolean existsByEmail(String email);
  boolean existsByTelefone(String telefone);
  Optional<Cliente> findByEmail(String email);
  Optional<Cliente> findByTelefone(String telefone);

}
