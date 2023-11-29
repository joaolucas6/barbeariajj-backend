package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.PedidoAgendamento;
import com.joaolucas.barbeariajj.models.entities.Servico;
import com.joaolucas.barbeariajj.models.enums.MetodoPagamento;
import com.joaolucas.barbeariajj.models.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PedidoAgendamentoDTO {

    private Long id;
    private Long clienteId;
    private Long barbeiroId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private List<Long> servicosId;
    private MetodoPagamento metodoPagamento;
    private Status status;
    private String exigenciasDoCliente;
    private LocalDateTime criadoEm;

    public PedidoAgendamentoDTO(){

    }

    public PedidoAgendamentoDTO(PedidoAgendamento pedidoAgendamento) {
        setId(pedidoAgendamento.getId());
        if(pedidoAgendamento.getCliente() != null) setClienteId(pedidoAgendamento.getCliente().getId());
        if(pedidoAgendamento.getBarbeiro() != null) setBarbeiroId(pedidoAgendamento.getBarbeiro().getId());
        setHorarioInicio(pedidoAgendamento.getHorarioInicio());
        setHorarioFim(pedidoAgendamento.getHorarioFim());
        if(pedidoAgendamento.getServicos() != null) setServicosId(pedidoAgendamento.getServicos().stream().map(Servico::getId).toList());
        setMetodoPagamento(pedidoAgendamento.getMetodoPagamento());
        setStatus(pedidoAgendamento.getStatus());
        setExigenciasDoCliente(pedidoAgendamento.getExigenciasDoCliente());
        setCriadoEm(pedidoAgendamento.getCriadoEm());
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
        PedidoAgendamentoDTO that = (PedidoAgendamentoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(clienteId, that.clienteId) && Objects.equals(barbeiroId, that.barbeiroId) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioFim, that.horarioFim) && Objects.equals(servicosId, that.servicosId) && metodoPagamento == that.metodoPagamento && status == that.status && Objects.equals(exigenciasDoCliente, that.exigenciasDoCliente) && Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clienteId, barbeiroId, horarioInicio, horarioFim, servicosId, metodoPagamento, status, exigenciasDoCliente, criadoEm);
    }

    @Override
    public String toString() {
        return "PedidoAgendamentoDTO{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", barbeiroId=" + barbeiroId +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", servicosId=" + servicosId +
                ", metodoPagamento=" + metodoPagamento +
                ", status=" + status +
                ", exigenciasDoCliente='" + exigenciasDoCliente + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }


}
