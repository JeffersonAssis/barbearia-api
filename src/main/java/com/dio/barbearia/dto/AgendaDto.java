package com.dio.barbearia.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.OffsetDateTime;
import com.dio.barbearia.model.Agenda;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AgendaDto {

  private Long id;
 
 
  @NotNull(message = "O Data e Hora é obrigatório")
  private OffsetDateTime inicio;
  @NotNull(message = "O Data e Hora é obrigatório")
  private OffsetDateTime fim;

  private Integer dia;

  private Long clienteId;

  private String clienteNome;
  
  private ClienteDto cliente;


  public Agenda dtoToAgenda(){
    Agenda agenda = new Agenda();
    agenda.setId(this.id);
    agenda.setInicio(this.inicio);
    agenda.setFim(this.fim);
    agenda.setCliente(this.cliente.dtoToCliente());
    return agenda;
  }

}
