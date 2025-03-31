package com.dio.barbearia.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AgendaDtoInsert {

  
  @NotNull(message = "O Data e Hora é obrigatório")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Sao_Paulo")
  private OffsetDateTime inicio;
  @NotNull(message = "O Data e Hora é obrigatório")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Sao_Paulo")
  private  OffsetDateTime fim;
  
  private Long  clienteId;

}
