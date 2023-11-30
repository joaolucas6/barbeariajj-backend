package com.joaolucas.barbeariajj.repositories;

import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
}
