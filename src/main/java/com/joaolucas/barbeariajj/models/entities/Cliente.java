package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends User{

    @OneToMany(mappedBy = "cliente")
    private List<PedidoAgendamento> pedidosAgendamento = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Cliente(Long id, String nome, String sobrenome, String email, String senha, String numeroTelefone, Genero genero, String cpf, Role role) {
        super(id, nome, sobrenome, email, senha, numeroTelefone, genero, cpf, role);
    }
}
