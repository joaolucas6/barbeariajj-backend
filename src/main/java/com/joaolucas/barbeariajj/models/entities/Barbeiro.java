package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("Barbeiro")
public class Barbeiro extends User {

    @OneToMany(mappedBy = "barbeiro")
    private List<PedidoAgendamento> pedidosAgendamento = new ArrayList<>();
    @OneToMany(mappedBy = "barbeiro")
    private List<Agendamento> agendamentos = new ArrayList<>();
    @OneToMany(mappedBy = "barbeiro")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    @ManyToMany(mappedBy = "barbeiro")
    private List<Servico> especialidades = new ArrayList<>();

    public Barbeiro() {
    }

    public Barbeiro(Long id, String nome, String sobrenome, String email, String senha, String numeroTelefone, Genero genero, String cpf, Role role) {
        super(id, nome, sobrenome, email, senha, numeroTelefone, genero, cpf, role);
    }

    public List<PedidoAgendamento> getPedidosAgendamento() {
        return pedidosAgendamento;
    }

    public void setPedidosAgendamento(List<PedidoAgendamento> pedidosAgendamento) {
        this.pedidosAgendamento = pedidosAgendamento;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Servico> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Servico> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Barbeiro barbeiro = (Barbeiro) o;
        return Objects.equals(pedidosAgendamento, barbeiro.pedidosAgendamento) && Objects.equals(agendamentos, barbeiro.agendamentos) && Objects.equals(avaliacoes, barbeiro.avaliacoes) && Objects.equals(especialidades, barbeiro.especialidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pedidosAgendamento, agendamentos, avaliacoes, especialidades);
    }

    @Override
    public String toString() {
        return "Barbeiro{" +
                "pedidosAgendamento=" + pedidosAgendamento +
                ", agendamentos=" + agendamentos +
                ", avaliacoes=" + avaliacoes +
                ", especialidades=" + especialidades +
                '}';
    }


}
