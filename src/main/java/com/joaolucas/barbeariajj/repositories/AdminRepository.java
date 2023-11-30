package com.joaolucas.barbeariajj.repositories;

import com.joaolucas.barbeariajj.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
