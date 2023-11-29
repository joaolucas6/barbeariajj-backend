package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.*;
import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;

import java.util.List;
import java.util.Objects;

public class BarbeiroDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String numeroTelefone;
    private Genero genero;
    private String cpf;
    private Role role;
    private List<Long> pedidosAgendamento;
    private List<Long> agendamentos;
    private List<Long> avaliacoes;
    private List<Long> especialidades;

    public BarbeiroDTO(Barbeiro barbeiro){
        setId(barbeiro.getId());
        setNome(barbeiro.getNome());
        setSobrenome(barbeiro.getSobrenome());
        setEmail(barbeiro.getEmail());
        setNumeroTelefone(barbeiro.getNumeroTelefone());
        setGenero(barbeiro.getGenero());
        setCpf(barbeiro.getCpf());
        setRole(Role.BARBEIRO);
        if(barbeiro.getPedidosAgendamento() != null) setPedidosAgendamento(barbeiro.getPedidosAgendamento().stream().map(PedidoAgendamento::getId).toList());
        if(barbeiro.getAgendamentos() != null) setAgendamentos(barbeiro.getAgendamentos().stream().map(Agendamento::getId).toList());
        if(barbeiro.getAvaliacoes() != null) setAvaliacoes(barbeiro.getAvaliacoes().stream().map(Avaliacao::getId).toList());
        if(barbeiro.getEspecialidades() != null) setEspecialidades(barbeiro.getEspecialidades().stream().map(Servico::getId).toList());
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getPedidosAgendamento() {
        return pedidosAgendamento;
    }

    public void setPedidosAgendamento(List<Long> pedidosAgendamento) {
        this.pedidosAgendamento = pedidosAgendamento;
    }

    public List<Long> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Long> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Long> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Long> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Long> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Long> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarbeiroDTO that = (BarbeiroDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sobrenome, that.sobrenome) && Objects.equals(email, that.email) && Objects.equals(numeroTelefone, that.numeroTelefone) && genero == that.genero && Objects.equals(cpf, that.cpf) && role == that.role && Objects.equals(pedidosAgendamento, that.pedidosAgendamento) && Objects.equals(agendamentos, that.agendamentos) && Objects.equals(avaliacoes, that.avaliacoes) && Objects.equals(especialidades, that.especialidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, email, numeroTelefone, genero, cpf, role, pedidosAgendamento, agendamentos, avaliacoes, especialidades);
    }

    @Override
    public String toString() {
        return "BarbeiroDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", genero=" + genero +
                ", cpf='" + cpf + '\'' +
                ", role=" + role +
                ", pedidosAgendamento=" + pedidosAgendamento +
                ", agendamentos=" + agendamentos +
                ", avaliacoes=" + avaliacoes +
                ", especialidades=" + especialidades +
                '}';
    }

}
