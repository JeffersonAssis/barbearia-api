package com.dio.barbearia.model;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.OffsetDateTime;
import com.dio.barbearia.dto.AgendaDto;


@Getter
@Setter
@Entity
@Table(name = "agenda", uniqueConstraints = {
  @UniqueConstraint(name = "inicio_fim_uk", columnNames = {"inicio", "fim"})
 
})
@ToString
public class Agenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private OffsetDateTime inicio;
  @Column(nullable = false)
  private OffsetDateTime fim;
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;


  public AgendaDto agendaToDto(){
    AgendaDto agendaDto = new AgendaDto();
    agendaDto.setId(this.id);
    agendaDto.setInicio(this.inicio);
    agendaDto.setFim(this.fim);
    agendaDto.setDia(this.inicio.getDayOfMonth());
    agendaDto.setClienteId(this.getCliente().getId());
    agendaDto.setClienteNome(this.getCliente().getNome());
    
    return agendaDto;
  }

}
