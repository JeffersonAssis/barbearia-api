package com.dio.barbearia.repository;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dio.barbearia.model.Agenda;

@Repository
public interface AgendaRepsository  extends JpaRepository<Agenda, Long> {

  boolean existsByInicioAndFim(OffsetDateTime  inicio, OffsetDateTime  fim);

  List<Agenda> findByInicioGreaterThanEqualAndFimLessThanEqualOrderByInicioAscFimAsc(OffsetDateTime  inicio, OffsetDateTime  fim);
}
