package com.joaolucas.barbeariajj.repositories;

import com.joaolucas.barbeariajj.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
