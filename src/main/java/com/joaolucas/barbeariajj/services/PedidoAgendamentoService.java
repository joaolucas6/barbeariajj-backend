package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.PedidoAgendamentoDTO;
import com.joaolucas.barbeariajj.models.entities.*;
import com.joaolucas.barbeariajj.models.enums.Status;
import com.joaolucas.barbeariajj.repositories.*;
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoAgendamentoService {

    private final PedidoAgendamentoRepository pedidoAgendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final Clock clock;

    public List<PedidoAgendamentoDTO> encontrarTodos(){
        return pedidoAgendamentoRepository.findAll().stream().map(PedidoAgendamentoDTO::new).toList();
    }

    public PedidoAgendamentoDTO encontrarPorId(Long id){
        return new PedidoAgendamentoDTO(pedidoAgendamentoRepository.findById(id).orElseThrow());
    }

    public PedidoAgendamentoDTO criar(PedidoAgendamentoDTO pedidoAgendamentoDTO){
        if(!ValidacaoDeDados.pedidoAgendamentoValido(pedidoAgendamentoDTO, false)) throw new RuntimeException();

        Cliente cliente = clienteRepository.findById(pedidoAgendamentoDTO.getClienteId()).orElseThrow();
        Barbeiro barbeiro = barbeiroRepository.findById(pedidoAgendamentoDTO.getBarbeiroId()).orElseThrow();

        PedidoAgendamento pedidoAgendamento = new PedidoAgendamento();


        if(!barbeiro.getAgendamentos().stream().filter(a -> pedidoAgendamentoDTO.getHorarioInicio().isAfter(a.getHorarioInicio()) && pedidoAgendamentoDTO.getHorarioFim().isBefore(a.getHorarioFim())).toList().isEmpty()) throw new RuntimeException();

        pedidoAgendamento.setHorarioInicio(pedidoAgendamentoDTO.getHorarioInicio());
        pedidoAgendamento.setHorarioFim(pedidoAgendamentoDTO.getHorarioFim());
        pedidoAgendamento.setServicos(pedidoAgendamentoDTO.getServicosId().stream().map(id -> servicoRepository.findById(id).orElseThrow()).toList());
        pedidoAgendamento.setMetodoPagamento(pedidoAgendamentoDTO.getMetodoPagamento());
        pedidoAgendamento.setStatus(Status.PENDENTE);
        pedidoAgendamento.setExigenciasDoCliente(pedidoAgendamentoDTO.getExigenciasDoCliente());
        pedidoAgendamento.setCriadoEm(LocalDateTime.now(clock));

        PedidoAgendamento pedidoAgendamentoSalvo = pedidoAgendamentoRepository.save(pedidoAgendamento);
        cliente.getPedidosAgendamento().add(pedidoAgendamentoSalvo);
        barbeiro.getPedidosAgendamento().add(pedidoAgendamentoSalvo);

        clienteRepository.save(cliente);
        barbeiroRepository.save(barbeiro);

        return new PedidoAgendamentoDTO(pedidoAgendamentoSalvo);
    }

    public PedidoAgendamentoDTO atualizar(Long id, PedidoAgendamentoDTO pedidoAgendamentoDTO){
        if(!ValidacaoDeDados.pedidoAgendamentoValido(pedidoAgendamentoDTO, true)) throw new RuntimeException();

        PedidoAgendamento pedidoAgendamento = pedidoAgendamentoRepository.findById(id).orElseThrow();

        if(pedidoAgendamentoDTO.getHorarioInicio() != null && pedidoAgendamentoDTO.getHorarioFim() != null){
            if(!pedidoAgendamento.getBarbeiro().getAgendamentos().stream().filter(a -> pedidoAgendamentoDTO.getHorarioInicio().isAfter(a.getHorarioInicio()) && pedidoAgendamentoDTO.getHorarioFim().isBefore(a.getHorarioFim())).toList().isEmpty()) throw new RuntimeException();
            if(pedidoAgendamento.getStatus() != Status.PENDENTE) throw new RuntimeException();

            pedidoAgendamento.setHorarioInicio(pedidoAgendamentoDTO.getHorarioInicio());
            pedidoAgendamento.setHorarioFim(pedidoAgendamentoDTO.getHorarioFim());
        }

        if(pedidoAgendamentoDTO.getServicosId() != null) pedidoAgendamento.setServicos(pedidoAgendamentoDTO.getServicosId().stream().map(ida -> servicoRepository.findById(id).orElseThrow()).toList());

        if(pedidoAgendamentoDTO.getMetodoPagamento() != null) pedidoAgendamento.setMetodoPagamento(pedidoAgendamentoDTO.getMetodoPagamento());

        if(pedidoAgendamentoDTO.getExigenciasDoCliente() != null) pedidoAgendamento.setExigenciasDoCliente(pedidoAgendamentoDTO.getExigenciasDoCliente());

        return new PedidoAgendamentoDTO(pedidoAgendamentoRepository.save(pedidoAgendamento));
    }

    public void deletar(Long id){
        PedidoAgendamento pedidoAgendamento = pedidoAgendamentoRepository.findById(id).orElseThrow();
        if(pedidoAgendamento.getStatus() != Status.PENDENTE) throw new RuntimeException();

        pedidoAgendamentoRepository.delete(pedidoAgendamento);
    }

    public void aceitarPedido(Long id){
        PedidoAgendamento pedidoAgendamento = pedidoAgendamentoRepository.findById(id).orElseThrow();
        Barbeiro barbeiro = pedidoAgendamento.getBarbeiro();
        Cliente cliente = pedidoAgendamento.getCliente();

        pedidoAgendamento.setStatus(Status.ACEITO);

        Agendamento agendamento = new Agendamento();
        agendamento.setHorarioInicio(pedidoAgendamento.getHorarioInicio());
        agendamento.setHorarioFim(pedidoAgendamento.getHorarioFim());
        agendamento.setServicos(pedidoAgendamento.getServicos());
        agendamento.setMetodoPagamento(pedidoAgendamento.getMetodoPagamento());
        agendamento.setStatus(Status.CONFIRMADO);
        agendamento.setExigenciasDoCliente(pedidoAgendamento.getExigenciasDoCliente());
        agendamento.setPrecoTotal(pedidoAgendamento.getServicos().stream().map(Servico::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add));
        agendamento.setCriadoEm(LocalDateTime.now(clock));
        agendamento.setBarbeiro(barbeiro);
        agendamento.setCliente(cliente);

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        barbeiro.getAgendamentos().add(agendamentoSalvo);
        cliente.getAgendamentos().add(agendamentoSalvo);

        barbeiroRepository.save(barbeiro);
        clienteRepository.save(cliente);
    }

    public void negarPedido(Long id){
        PedidoAgendamento pedidoAgendamento = pedidoAgendamentoRepository.findById(id).orElseThrow();
        pedidoAgendamento.setStatus(Status.NEGADO);

        pedidoAgendamentoRepository.save(pedidoAgendamento);
    }
}
