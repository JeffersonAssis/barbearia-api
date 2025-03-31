package com.dio.barbearia.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dio.barbearia.dto.AgendaDto;
import com.dio.barbearia.dto.AgendaDtoInsert;
import com.dio.barbearia.exception.ObjectNotFoundException;
import com.dio.barbearia.model.Agenda;
import com.dio.barbearia.repository.AgendaRepsository;
import com.dio.barbearia.service.interfac.IAgendaService;

@Service
public class AgendaService implements IAgendaService {

  @Autowired
  private AgendaRepsository agendaRepsository;
  @Autowired
  private ClienteService clienteService;

  @Override
  public AgendaDto findById(Long id) {
   var a = agendaRepsository.findById(id);
   if(a.isPresent()) {
     return a.get().agendaToDto();
   }
     throw new ObjectNotFoundException("Agenda não encontrada");
  }

  @Override
  public AgendaDto save(AgendaDtoInsert agenda) {
      existsData(agenda.getInicio(), agenda.getFim());
      Agenda a = new Agenda();
      a.setInicio(agenda.getInicio());
      a.setFim(agenda.getFim());
      a.setCliente(clienteService.findById(agenda.getClienteId()).dtoToCliente());
      
      return agendaRepsository.save(a).agendaToDto();
  }

  @Override
  public void delete(Long id) {
      agendaRepsository.deleteById(id);;
    
  }

  @Override
  public void existsData(OffsetDateTime  inicio, OffsetDateTime  fim) {
     
    if(agendaRepsository.existsByInicioAndFim(inicio, fim)) {
        throw new IllegalArgumentException("Já existe uma agenda cadastrada nesse horário");
      }
 }

 @Override
  public List<AgendaDto> buscarData(OffsetDateTime  inicio, OffsetDateTime  fim) {
    existsData(inicio, fim);
    return agendaRepsository.findByInicioGreaterThanEqualAndFimLessThanEqualOrderByInicioAscFimAsc(inicio, fim).stream()
        .map(Agenda::agendaToDto).collect(Collectors.toList());
  }

}
