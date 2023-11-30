package com.joaolucas.barbeariajj.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco")
    private BigDecimal preco;
    @OneToMany(mappedBy = "servico")
    private List<PedidoAgendamento> pedidosAgendamento = new ArrayList<>();
    @ManyToMany(mappedBy = "servicos")
    private List<Agendamento> agendamentos = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "barbeiros_especialidades",
            joinColumns = @JoinColumn(name = "especialidade_id"),
            inverseJoinColumns = @JoinColumn(name = "barbeiro_id")
    )
    private List<Barbeiro> barbeirosEspecializados = new ArrayList<>();

    public Servico() {
    }

    public Servico(Long id, String nome, String descricao, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
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

    public List<Barbeiro> getBarbeirosEspecializados() {
        return barbeirosEspecializados;
    }

    public void setBarbeirosEspecializados(List<Barbeiro> barbeirosEspecializados) {
        this.barbeirosEspecializados = barbeirosEspecializados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id) && Objects.equals(nome, servico.nome) && Objects.equals(descricao, servico.descricao) && Objects.equals(preco, servico.preco) && Objects.equals(pedidosAgendamento, servico.pedidosAgendamento) && Objects.equals(agendamentos, servico.agendamentos) && Objects.equals(barbeirosEspecializados, servico.barbeirosEspecializados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, pedidosAgendamento, agendamentos, barbeirosEspecializados);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", pedidosAgendamento=" + pedidosAgendamento +
                ", agendamentos=" + agendamentos +
                ", barbeirosEspecializados=" + barbeirosEspecializados +
                '}';
    }
}
