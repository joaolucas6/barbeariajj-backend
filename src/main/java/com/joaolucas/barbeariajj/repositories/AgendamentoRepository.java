package com.joaolucas.barbeariajj.repositories;

import com.joaolucas.barbeariajj.models.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
