package com.joaolucas.barbeariajj.models.dto;

import com.joaolucas.barbeariajj.models.entities.Admin;
import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;

import java.util.Objects;

public class AdminDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String numeroTelefone;
    private Genero genero;
    private String cpf;
    private Role role;

    public AdminDTO(Admin admin){
        setId(admin.getId());
        setNome(admin.getNome());
        setSobrenome(admin.getSobrenome());
        setEmail(admin.getEmail());
        setNumeroTelefone(admin.getNumeroTelefone());
        setGenero(admin.getGenero());
        setCpf(admin.getCpf());
        setRole(Role.ADMIN);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(id, adminDTO.id) && Objects.equals(nome, adminDTO.nome) && Objects.equals(sobrenome, adminDTO.sobrenome) && Objects.equals(email, adminDTO.email) && Objects.equals(numeroTelefone, adminDTO.numeroTelefone) && genero == adminDTO.genero && Objects.equals(cpf, adminDTO.cpf) && role == adminDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, email, numeroTelefone, genero, cpf, role);
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", genero=" + genero +
                ", cpf='" + cpf + '\'' +
                ", role=" + role +
                '}';
    }
}
