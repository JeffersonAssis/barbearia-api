package com.dio.barbearia.dto;

import com.dio.barbearia.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteDtoInsert {

  @NotNull(message = "O nome é obrigatório")
  @NotBlank(message = "O nome não pode ser vazio")
  private String nome;
  @Email(message = "O email é inválido")
  private String email;
  @NotNull(message = "O telefone é obrigatório")
  @NotBlank(message = "O telefone não pode ser vazio")
  private String telefone;
  
 
  public Cliente dtoToCliente() {
  
    Cliente cliente = new Cliente();
    cliente.setNome(this.nome);
    cliente.setEmail(this.email);
    cliente.setTelefone(this.telefone);
   
    return cliente;
  }



}
