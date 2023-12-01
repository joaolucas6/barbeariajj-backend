package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AdminDTO;
import com.joaolucas.barbeariajj.models.entities.Admin;
import com.joaolucas.barbeariajj.repositories.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    private AdminService emTeste;
    @Mock
    private AdminRepository adminRepository;
    private Admin admin;

    @BeforeEach
    void setUp() {
        emTeste = new AdminService(adminRepository);
        admin = new Admin();
    }

    @Test
    void deveRetornarTodosOsAdmins() {
        when(adminRepository.findAll()).thenReturn(List.of(admin));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new AdminDTO(admin)));
    }

    @Test
    void deveRetornarBabeiroPorId() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new AdminDTO(admin));
    }

    @Test
    void deveAtualizarAdmin() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        AdminDTO adminDTO = new AdminDTO(admin);
        adminDTO.setNome("eu quero chorar");

        var resultado = emTeste.atualizar(1L, adminDTO);

        assertThat(resultado).isNotNull();
        assertThat(admin.getNome()).isEqualTo("eu quero chorar");
    }

    @Test
    void deveDeletarAdmin() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        emTeste.deletar(1L);
        verify(adminRepository, times(1)).delete(admin);
    }

}