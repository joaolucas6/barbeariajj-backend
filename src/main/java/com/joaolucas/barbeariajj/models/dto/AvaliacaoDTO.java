package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.Avaliacao;

import java.util.Objects;

public class AvaliacaoDTO {
    private Long id;
    private Long clienteId;
    private Long barbeiroId;
    private Long agendamentoId;
    private Double nota;
    private String comentarios;

    public AvaliacaoDTO(Avaliacao avaliacao){
        setId(avaliacao.getId());
        if(avaliacao.getCliente() != null) setClienteId(avaliacao.getCliente().getId());
        if(avaliacao.getBarbeiro() != null) setBarbeiroId(avaliacao.getBarbeiro().getId());
        if(avaliacao.getAgendamento() != null) setAgendamentoId(avaliacao.getAgendamento().getId());
        setNota(avaliacao.getNota());
        setComentarios(avaliacao.getComentarios());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getBarbeiroId() {
        return barbeiroId;
    }

    public void setBarbeiroId(Long barbeiroId) {
        this.barbeiroId = barbeiroId;
    }

    public Long getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Long agendamentoId) {
        this.agendamentoId = agendamentoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoDTO that = (AvaliacaoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(clienteId, that.clienteId) && Objects.equals(barbeiroId, that.barbeiroId) && Objects.equals(agendamentoId, that.agendamentoId) && Objects.equals(nota, that.nota) && Objects.equals(comentarios, that.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, barbeiroId, agendamentoId, nota, comentarios);
    }

    @Override
    public String toString() {
        return "AvaliacaoDTO{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", barbeiroId=" + barbeiroId +
                ", agendamentoId=" + agendamentoId +
                ", nota=" + nota +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
