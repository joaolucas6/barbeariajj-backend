package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AgendamentoDTO;
import com.joaolucas.barbeariajj.models.dto.PedidoAgendamentoDTO;
import com.joaolucas.barbeariajj.models.entities.Agendamento;
import com.joaolucas.barbeariajj.repositories.AgendamentoRepository;
import com.joaolucas.barbeariajj.repositories.ServicoRepository;
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
class AgendamentoServiceTest {

    private AgendamentoService emTeste;
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private ServicoRepository servicoRepository;
    private Agendamento agendamento;

    @BeforeEach
    void setUp() {
        emTeste = new AgendamentoService(agendamentoRepository, servicoRepository);
        agendamento = new Agendamento();
    }

    @Test
    void deveRetornarTodosOsAgendamentos() {
        when(agendamentoRepository.findAll()).thenReturn(List.of(agendamento));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new AgendamentoDTO(agendamento)));
    }

    @Test
    void deveRetornarAgendamentoPorId() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new AgendamentoDTO(agendamento));
    }

    @Test
    void deveAtualizarAgendamento() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento);
        agendamentoDTO.setExigenciasDoCliente("eu quero chorar");

        var resultado = emTeste.atualizar(1L, agendamentoDTO);

        assertThat(resultado).isNotNull();
        assertThat(agendamento.getExigenciasDoCliente()).isEqualTo("eu quero chorar");
    }

    @Test
    void deveDeletarAgendamento() {
        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        emTeste.deletar(1L);
        verify(agendamentoRepository, times(1)).delete(agendamento);
    }
}