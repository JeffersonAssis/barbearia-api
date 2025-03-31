package com.dio.barbearia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AgendaResponseDto {

   private Integer ano;
   private Integer mes;
   private Object agenda;
}
