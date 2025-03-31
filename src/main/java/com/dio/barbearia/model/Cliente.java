package com.dio.barbearia.model;

import com.dio.barbearia.dto.ClienteDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "cliente", uniqueConstraints = {
  @UniqueConstraint(name = "email_uk", columnNames = "email"),
  @UniqueConstraint(name = "telefone_uk", columnNames = "telefone")
})
@ToString
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false, unique = true, length = 11)
  private String telefone;

  public ClienteDto clienteToDto() {
    ClienteDto clienteDto = new ClienteDto();
    clienteDto.setId(this.id);
    clienteDto.setNome(this.nome);
    clienteDto.setEmail(this.email);
    clienteDto.setTelefone(this.telefone);
    return clienteDto;

  }
}
