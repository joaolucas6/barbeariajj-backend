package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.Agendamento;
import com.joaolucas.barbeariajj.models.entities.Servico;
import com.joaolucas.barbeariajj.models.enums.MetodoPagamento;
import com.joaolucas.barbeariajj.models.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AgendamentoDTO {
    private Long id;
    private Long clienteId;
    private Long barbeiroId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private List<Long> servicosId;
    private MetodoPagamento metodoPagamento;
    private Status status;
    private String exigenciasDoCliente;
    private Long avaliacaoDoClienteId;
    private BigDecimal precoAdicional;
    private Double precoDescontado;
    private BigDecimal precoTotal;
    private LocalDateTime criadoEm;

    public AgendamentoDTO(Agendamento agendamento){
        setId(agendamento.getId());
        if(agendamento.getCliente() != null) setClienteId(agendamento.getCliente().getId());
        if(agendamento.getBarbeiro() != null) setBarbeiroId(agendamento.getBarbeiro().getId());
        setHorarioInicio(agendamento.getHorarioInicio());
        setHorarioFim(agendamento.getHorarioFim());
        if(agendamento.getServicos() != null) setServicosId(agendamento.getServicos().stream().map(Servico::getId).toList());
        setMetodoPagamento(agendamento.getMetodoPagamento());
        setStatus(agendamento.getStatus());
        setExigenciasDoCliente(agendamento.getExigenciasDoCliente());
        if(agendamento.getAvaliacaoDoCliente() != null) setAvaliacaoDoClienteId(agendamento.getAvaliacaoDoCliente().getId());
        setPrecoAdicional(agendamento.getPrecoAdicional());
        setPrecoDescontado(agendamento.getPrecoDescontado());
        setPrecoTotal(agendamento.getPrecoTotal());
        setCriadoEm(agendamento.getCriadoEm());
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

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDateTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public List<Long> getServicosId() {
        return servicosId;
    }

    public void setServicosId(List<Long> servicosId) {
        this.servicosId = servicosId;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getExigenciasDoCliente() {
        return exigenciasDoCliente;
    }

    public void setExigenciasDoCliente(String exigenciasDoCliente) {
        this.exigenciasDoCliente = exigenciasDoCliente;
    }

    public Long getAvaliacaoDoClienteId() {
        return avaliacaoDoClienteId;
    }

    public void setAvaliacaoDoClienteId(Long avaliacaoDoClienteId) {
        this.avaliacaoDoClienteId = avaliacaoDoClienteId;
    }

    public BigDecimal getPrecoAdicional() {
        return precoAdicional;
    }

    public void setPrecoAdicional(BigDecimal precoAdicional) {
        this.precoAdicional = precoAdicional;
    }

    public Double getPrecoDescontado() {
        return precoDescontado;
    }

    public void setPrecoDescontado(Double precoDescontado) {
        this.precoDescontado = precoDescontado;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
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
        AgendamentoDTO that = (AgendamentoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(clienteId, that.clienteId) && Objects.equals(barbeiroId, that.barbeiroId) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioFim, that.horarioFim) && Objects.equals(servicosId, that.servicosId) && metodoPagamento == that.metodoPagamento && status == that.status && Objects.equals(exigenciasDoCliente, that.exigenciasDoCliente) && Objects.equals(avaliacaoDoClienteId, that.avaliacaoDoClienteId) && Objects.equals(precoAdicional, that.precoAdicional) && Objects.equals(precoDescontado, that.precoDescontado) && Objects.equals(precoTotal, that.precoTotal) && Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, barbeiroId, horarioInicio, horarioFim, servicosId, metodoPagamento, status, exigenciasDoCliente, avaliacaoDoClienteId, precoAdicional, precoDescontado, precoTotal, criadoEm);
    }

    @Override
    public String toString() {
        return "AgendamentoDTO{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", barbeiroId=" + barbeiroId +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", servicosId=" + servicosId +
                ", metodoPagamento=" + metodoPagamento +
                ", status=" + status +
                ", exigenciasDoCliente='" + exigenciasDoCliente + '\'' +
                ", avaliacaoDoClienteId=" + avaliacaoDoClienteId +
                ", precoAdicional=" + precoAdicional +
                ", precoDescontado=" + precoDescontado +
                ", precoTotal=" + precoTotal +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
