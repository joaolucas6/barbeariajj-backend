package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.MetodoPagamento;
import com.joaolucas.barbeariajj.models.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
            name = "servicos_agendamento",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "exigencias_do_cliente")
    private String exigenciasDoCliente;
    @OneToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacaoDoCliente;
    @Column(name = "preco_adicional")
    private BigDecimal precoAdicional;
    @Column(name = "preco_descontado")
    private Double precoDescontado;
    @Column(name = "preco_total")
    private BigDecimal precoTotal;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    public Agendamento(Long id, Cliente cliente, Barbeiro barbeiro, LocalDateTime horarioInicio, LocalDateTime horarioFim, List<Servico> servicos, MetodoPagamento metodoPagamento, Status status, String exigenciasDoCliente, Avaliacao avaliacaoDoCliente, BigDecimal precoAdicional, Double precoDescontado, BigDecimal precoTotal, LocalDateTime criadoEm) {
        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.servicos = servicos;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
        this.exigenciasDoCliente = exigenciasDoCliente;
        this.avaliacaoDoCliente = avaliacaoDoCliente;
        this.precoAdicional = precoAdicional;
        this.precoDescontado = precoDescontado;
        this.precoTotal = precoTotal;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Avaliacao getAvaliacaoDoCliente() {
        return avaliacaoDoCliente;
    }

    public void setAvaliacaoDoCliente(Avaliacao avaliacaoDoCliente) {
        this.avaliacaoDoCliente = avaliacaoDoCliente;
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
        Agendamento that = (Agendamento) o;
        return Objects.equals(cliente, that.cliente) && Objects.equals(barbeiro, that.barbeiro) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioFim, that.horarioFim) && Objects.equals(servicos, that.servicos) && metodoPagamento == that.metodoPagamento && status == that.status && Objects.equals(exigenciasDoCliente, that.exigenciasDoCliente) && Objects.equals(avaliacaoDoCliente, that.avaliacaoDoCliente) && Objects.equals(precoAdicional, that.precoAdicional) && Objects.equals(precoDescontado, that.precoDescontado) && Objects.equals(precoTotal, that.precoTotal) && Objects.equals(criadoEm, that.criadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, barbeiro, horarioInicio, horarioFim, servicos, metodoPagamento, status, exigenciasDoCliente, avaliacaoDoCliente, precoAdicional, precoDescontado, precoTotal, criadoEm);
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "cliente=" + cliente +
                ", barbeiro=" + barbeiro +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", servicos=" + servicos +
                ", metodoPagamento=" + metodoPagamento +
                ", status=" + status +
                ", exigenciasDoCliente='" + exigenciasDoCliente + '\'' +
                ", avaliacaoDoCliente=" + avaliacaoDoCliente +
                ", precoAdicional=" + precoAdicional +
                ", precoDescontado=" + precoDescontado +
                ", precoTotal=" + precoTotal +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
