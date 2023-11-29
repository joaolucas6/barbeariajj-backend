package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.MetodoPagamento;
import com.joaolucas.barbeariajj.models.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pedidos_agendamento")
public class PedidoAgendamento {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;
    @Column(name = "horario_fim")
    private LocalDateTime horarioFim;
    @ManyToMany
    @JoinTable(
            name = "servicos_pedido_agendamento",
            joinColumns = @JoinColumn(name = "pedido_agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "exigencias_do_cliente")
    private String exigenciasDoCliente;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public PedidoAgendamento(Cliente cliente, Barbeiro barbeiro, LocalDateTime horarioInicio, LocalDateTime horarioFim, List<Servico> servicos, MetodoPagamento metodoPagamento, Status status, String exigenciasDoCliente, LocalDateTime criadoEm) {
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.servicos = servicos;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
        this.exigenciasDoCliente = exigenciasDoCliente;
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

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
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
        PedidoAgendamento that = (PedidoAgendamento) o;
        return Objects.equals(cliente, that.cliente) && Objects.equals(barbeiro, that.barbeiro) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioFim, that.horarioFim) && Objects.equals(servicos, that.servicos) && metodoPagamento == that.metodoPagamento && status == that.status && Objects.equals(exigenciasDoCliente, that.exigenciasDoCliente) && Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, barbeiro, horarioInicio, horarioFim, servicos, metodoPagamento, status, exigenciasDoCliente, criadoEm);
    }

    @Override
    public String toString() {
        return "PedidoAgendamento{" +
                "cliente=" + cliente +
                ", barbeiro=" + barbeiro +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", servicos=" + servicos +
                ", metodoPagamento=" + metodoPagamento +
                ", status=" + status +
                ", exigenciasDoCliente='" + exigenciasDoCliente + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
