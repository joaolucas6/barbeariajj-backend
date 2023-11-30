package com.joaolucas.barbeariajj.models.entities;

import com.joaolucas.barbeariajj.models.enums.Genero;
import com.joaolucas.barbeariajj.models.enums.Role;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "numero_telefone")
    private String numeroTelefone;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Column(name = "cpf")
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Long id, String nome, String sobrenome, String email, String senha, String numeroTelefone, Genero genero, String cpf, Role role) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.genero = genero;
        this.cpf = cpf;
        this.role = role;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nome, user.nome) && Objects.equals(sobrenome, user.sobrenome) && Objects.equals(email, user.email) && Objects.equals(senha, user.senha) && Objects.equals(numeroTelefone, user.numeroTelefone) && genero == user.genero && Objects.equals(cpf, user.cpf) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, email, senha, numeroTelefone, genero, cpf, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", genero=" + genero +
                ", cpf='" + cpf + '\'' +
                ", role=" + role +
                '}';
    }
}
