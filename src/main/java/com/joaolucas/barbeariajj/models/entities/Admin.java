package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User{
    public Admin(Long id, String nome, String sobrenome, String email, String senha, String numeroTelefone, Genero genero, String cpf, Role role) {
        super(id, nome, sobrenome, email, senha, numeroTelefone, genero, cpf, role);
    }
}
