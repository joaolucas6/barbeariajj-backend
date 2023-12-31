package com.joaolucas.barbeariajj.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;
    @Column(name = "nota")
    private Double nota;
    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Cliente cliente, Barbeiro barbeiro, Agendamento agendamento, Double nota, String comentarios, LocalDateTime criadoEm) {
        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.agendamento = agendamento;
        this.nota = nota;
        this.comentarios = comentarios;
        this.criadoEm = criadoEm;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id) && Objects.equals(cliente, avaliacao.cliente) && Objects.equals(barbeiro, avaliacao.barbeiro) && Objects.equals(agendamento, avaliacao.agendamento) && Objects.equals(nota, avaliacao.nota) && Objects.equals(comentarios, avaliacao.comentarios) && Objects.equals(criadoEm, avaliacao.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, barbeiro, agendamento, nota, comentarios, criadoEm);
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", barbeiro=" + barbeiro +
                ", agendamento=" + agendamento +
                ", nota=" + nota +
                ", comentarios='" + comentarios + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
