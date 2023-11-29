package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.Agendamento;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.PedidoAgendamento;
import com.joaolucas.barbeariajj.models.entities.Servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<Long> pedidosAgendamentoId = new ArrayList<>();
    private List<Long> agendamentosId = new ArrayList<>();
    private List<Long> barbeirosEspecializadosId = new ArrayList<>();

    public ServicoDTO (){

    }

    public ServicoDTO (Servico servico){
        setId(servico.getId());
        setNome(servico.getNome());
        setDescricao(servico.getDescricao());
        setPreco(servico.getPreco());
        if(servico.getPedidosAgendamento() != null) setPedidosAgendamentoId(servico.getPedidosAgendamento().stream().map(PedidoAgendamento::getId).toList());
        if(servico.getAgendamentos() != null) setAgendamentosId(servico.getAgendamentos().stream().map(Agendamento::getId).toList());
        if(servico.getBarbeirosEspecializados() != null) setBarbeirosEspecializadosId(servico.getBarbeirosEspecializados().stream().map(Barbeiro::getId).toList());
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

    public List<Long> getPedidosAgendamentoId() {
        return pedidosAgendamentoId;
    }

    public void setPedidosAgendamentoId(List<Long> pedidosAgendamentoId) {
        this.pedidosAgendamentoId = pedidosAgendamentoId;
    }

    public List<Long> getAgendamentosId() {
        return agendamentosId;
    }

    public void setAgendamentosId(List<Long> agendamentosId) {
        this.agendamentosId = agendamentosId;
    }

    public List<Long> getBarbeirosEspecializadosId() {
        return barbeirosEspecializadosId;
    }

    public void setBarbeirosEspecializadosId(List<Long> barbeirosEspecializadosId) {
        this.barbeirosEspecializadosId = barbeirosEspecializadosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicoDTO that = (ServicoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && Objects.equals(pedidosAgendamentoId, that.pedidosAgendamentoId) && Objects.equals(agendamentosId, that.agendamentosId) && Objects.equals(barbeirosEspecializadosId, that.barbeirosEspecializadosId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, pedidosAgendamentoId, agendamentosId, barbeirosEspecializadosId);
    }

    @Override
    public String toString() {
        return "ServicoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", pedidosAgendamentoId=" + pedidosAgendamentoId +
                ", agendamentosId=" + agendamentosId +
                ", barbeirosEspecializadosId=" + barbeirosEspecializadosId +
                '}';
    }
}
