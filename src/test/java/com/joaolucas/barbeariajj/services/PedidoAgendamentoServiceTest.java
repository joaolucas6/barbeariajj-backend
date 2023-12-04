package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.PedidoAgendamentoDTO;
import com.joaolucas.barbeariajj.models.entities.*;
import com.joaolucas.barbeariajj.models.enums.MetodoPagamento;
import com.joaolucas.barbeariajj.models.enums.Status;
import com.joaolucas.barbeariajj.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
class PedidoAgendamentoServiceTest {

    private PedidoAgendamentoService emTeste;
    @Mock
    private PedidoAgendamentoRepository pedidoAgendamentoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private BarbeiroRepository barbeiroRepository;
    @Mock
    private ServicoRepository servicoRepository;
    @Mock
    private AgendamentoRepository agendamentoRepository;

    private PedidoAgendamento pedidoAgendamento;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Agendamento agendamento;

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

    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {
        emTeste = new PedidoAgendamentoService(pedidoAgendamentoRepository, clienteRepository, barbeiroRepository, servicoRepository, agendamentoRepository, clock);
        pedidoAgendamento = new PedidoAgendamento();
    }

    @Test
    void deveEncontrarTodosOsPedidos() {
        when(pedidoAgendamentoRepository.findAll()).thenReturn(List.of(pedidoAgendamento));

        var resultado = emTeste.encontrarTodos();

        assertThat(resultado).isEqualTo(List.of(new PedidoAgendamentoDTO(pedidoAgendamento)));
    }

    @Test
    void deveEncontrarPedidoPorId() {
        when(pedidoAgendamentoRepository.findById(1L)).thenReturn(Optional.of(pedidoAgendamento));

        var resultado = emTeste.encontrarPorId(1L);

        assertThat(resultado).isEqualTo(new PedidoAgendamentoDTO(pedidoAgendamento));
    }

    @Test
    void deveCriarPedido() {
        cliente = new Cliente();
        cliente.setId(1L);
        barbeiro = new Barbeiro();
        barbeiro.setId(1L);

        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));
        when(pedidoAgendamentoRepository.save(pedidoAgendamento)).thenReturn(pedidoAgendamento);

        PedidoAgendamentoDTO pedidoAgendamentoDTO = new PedidoAgendamentoDTO();
        pedidoAgendamentoDTO.setServicosId(List.of());
        pedidoAgendamentoDTO.setClienteId(1L);
        pedidoAgendamentoDTO.setBarbeiroId(1L);

        pedidoAgendamentoDTO.setHorarioInicio(LocalDateTime.of(2023, 12, 4, 4, 25));
        pedidoAgendamentoDTO.setHorarioFim(LocalDateTime.of(2023, 12, 4, 5, 25));

        pedidoAgendamento.setHorarioInicio(LocalDateTime.of(2023, 12, 4, 4, 25));
        pedidoAgendamento.setHorarioFim(LocalDateTime.of(2023, 12, 4, 5, 25));
        pedidoAgendamento.setStatus(Status.PENDENTE);
        pedidoAgendamento.setServicos(List.of());
        pedidoAgendamento.setCriadoEm(LocalDateTime.now(clock));

        var resultado = emTeste.criar(pedidoAgendamentoDTO);

        assertThat(resultado).isNotNull();
    }

    @Test
    void deveAtualizarPedido() {
        when(pedidoAgendamentoRepository.findById(1L)).thenReturn(Optional.of(pedidoAgendamento));
        when(pedidoAgendamentoRepository.save(pedidoAgendamento)).thenReturn(pedidoAgendamento);

        PedidoAgendamentoDTO pedidoAgendamentoDTO = new PedidoAgendamentoDTO();
        pedidoAgendamentoDTO.setMetodoPagamento(MetodoPagamento.PIX);
        var resultado = emTeste.atualizar(1L, pedidoAgendamentoDTO);

        assertThat(pedidoAgendamento.getMetodoPagamento()).isEqualTo(MetodoPagamento.PIX);

    }

    @Test
    void deveDeletarPedido() {
        when(pedidoAgendamentoRepository.findById(1L)).thenReturn(Optional.of(pedidoAgendamento));
        pedidoAgendamento.setStatus(Status.PENDENTE);
        emTeste.deletar(1L);

        verify(pedidoAgendamentoRepository, times(1)).delete(pedidoAgendamento);
    }

    @Test
    void deveAceitarPedido() {
        agendamento = new Agendamento();
        barbeiro = new Barbeiro();
        cliente = new Cliente();

        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());
        when(pedidoAgendamentoRepository.findById(1L)).thenReturn(Optional.of(pedidoAgendamento));
        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        pedidoAgendamento.setBarbeiro(barbeiro);
        pedidoAgendamento.setCliente(cliente);

        pedidoAgendamento.setServicos(List.of());
        agendamento.setStatus(Status.CONFIRMADO);
        agendamento.setCriadoEm(LocalDateTime.now(clock));
        agendamento.setServicos(List.of());
        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);

        emTeste.aceitarPedido(1L);

        verify(agendamentoRepository, times(1)).save(agendamento);

    }

    @Test
    void deveNegarPedido() {
        when(pedidoAgendamentoRepository.findById(1L)).thenReturn(Optional.of(pedidoAgendamento));
        emTeste.negarPedido(1L);
        assertThat(pedidoAgendamento.getStatus()).isEqualTo(Status.NEGADO);
    }
}