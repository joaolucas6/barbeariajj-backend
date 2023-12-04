package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AvaliacaoDTO;
import com.joaolucas.barbeariajj.models.entities.Agendamento;
import com.joaolucas.barbeariajj.models.entities.Avaliacao;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.Cliente;
import com.joaolucas.barbeariajj.repositories.AgendamentoRepository;
import com.joaolucas.barbeariajj.repositories.AvaliacaoRepository;
import com.joaolucas.barbeariajj.repositories.BarbeiroRepository;
import com.joaolucas.barbeariajj.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    private AvaliacaoService emTeste;
    @Mock
    private AvaliacaoRepository avaliacaoRepository;
    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private BarbeiroRepository barbeiroRepository;
    @Mock
    private ClienteRepository clienteRepository;
    private Avaliacao avaliacao;
    private Agendamento agendamento;
    private Barbeiro barbeiro;
    private Cliente cliente;
    @Mock
    private Clock clock;

    private ZonedDateTime NOW = ZonedDateTime.of(
            2023,
            11,
            30,
            3,
            36,
            15,
            0,
            ZoneId.of("GMT")
    );


    @BeforeEach
    void setUp() {
        emTeste = new AvaliacaoService(avaliacaoRepository, agendamentoRepository, barbeiroRepository, clienteRepository, clock);
        avaliacao = new Avaliacao();
    }

    @Test
    void deveRetornarTodasAsAvaliacoes() {
        when(avaliacaoRepository.findAll()).thenReturn(List.of(avaliacao));

        var resultado = emTeste.encontrarTodos();

        assertThat(resultado).isEqualTo(List.of(new AvaliacaoDTO(avaliacao)));
    }

    @Test
    void deveRetornarAvaliacaoPorId() {
        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacao));

        var resultado = emTeste.encontrarPorId(1L);

        assertThat(resultado).isEqualTo(new AvaliacaoDTO(avaliacao));
    }

    @Test
    void deveCriarAvaliacao() {

        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());

        barbeiro = new Barbeiro();
        cliente = new Cliente();
        agendamento = new Agendamento();

        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);

        avaliacao.setCliente(cliente);
        avaliacao.setBarbeiro(barbeiro);
        avaliacao.setAgendamento(agendamento);
        avaliacao.setComentarios("thats all i wanted to doooooooooooooo");
        avaliacao.setNota(10.0);
        avaliacao.setCriadoEm(LocalDateTime.now(clock));

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(avaliacao);
        avaliacaoDTO.setComentarios("thats all i wanted to doooooooooooooo");
        avaliacaoDTO.setNota(10.0);

        var resultado = emTeste.criar(1L, avaliacaoDTO);



        assertThat(resultado).isNotNull();
    }

    @Test
    void deveAtualizarAvaliacao() {
        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacao));
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(avaliacao);
        avaliacaoDTO.setComentarios("gostei demais");

        var resultado = emTeste.atualizar(1L, avaliacaoDTO);

        assertThat(avaliacao.getComentarios()).isEqualTo("gostei demais");
    }

    @Test
    void deveRemoverAvaliacao() {
        agendamento = new Agendamento();
        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacao));
        avaliacao.setAgendamento(agendamento);

        emTeste.deletar(1L);

        verify(avaliacaoRepository, times(1)).delete(avaliacao);
    }
}