package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AgendamentoDTO;
import com.joaolucas.barbeariajj.models.dto.ClienteDTO;
import com.joaolucas.barbeariajj.models.entities.Cliente;
import com.joaolucas.barbeariajj.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    private ClienteService emTeste;
    @Mock
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        emTeste = new ClienteService(clienteRepository);
        cliente = new Cliente();
    }

    @Test
    void deveRetornarTodosOsAgendamentos() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new ClienteDTO(cliente)));
    }

    @Test
    void deveRetornarAgendamentoPorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new ClienteDTO(cliente));
    }

    @Test
    void deveAtualizarAgendamento() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        clienteDTO.setNome("eu quero chorar");

        var resultado = emTeste.atualizar(1L, clienteDTO);

        assertThat(resultado).isNotNull();
        assertThat(cliente.getNome()).isEqualTo("eu quero chorar");
    }

    @Test
    void deveDeletarAgendamento() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        emTeste.deletar(1L);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}