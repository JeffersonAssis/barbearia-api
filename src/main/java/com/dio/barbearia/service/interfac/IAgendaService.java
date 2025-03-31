package com.dio.barbearia.service.interfac;

import java.time.OffsetDateTime;
import java.util.List;
import com.dio.barbearia.dto.AgendaDto;
import com.dio.barbearia.dto.AgendaDtoInsert;

public interface IAgendaService {
   
   AgendaDto findById(Long id);
   AgendaDto save(AgendaDtoInsert agenda);
   void delete(Long id);
   void existsData(OffsetDateTime  inicio, OffsetDateTime  fim);
   List<AgendaDto> buscarData(OffsetDateTime  inicio, OffsetDateTime  fim);
  
  }
