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
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;
    private final Clock clock;

    public List<AvaliacaoDTO> encontrarTodos(){
        return avaliacaoRepository.findAll().stream().map(AvaliacaoDTO::new).toList();
    }

    public AvaliacaoDTO encontrarPorId(Long id){
        return new AvaliacaoDTO(avaliacaoRepository.findById(id).orElseThrow());
    }

    public AvaliacaoDTO criar(Long agendamentoId, AvaliacaoDTO avaliacaoDTO){
        if(!ValidacaoDeDados.avaliacaoValido(avaliacaoDTO, false)) throw new RuntimeException();

        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();
        Barbeiro barbeiro = agendamento.getBarbeiro();
        Cliente cliente = agendamento.getCliente();


        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setBarbeiro(barbeiro);
        avaliacao.setCliente(cliente);
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentarios(avaliacaoDTO.getComentarios());
        avaliacao.setAgendamento(agendamento);
        avaliacao.setCriadoEm(LocalDateTime.now(clock));

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        barbeiro.getAvaliacoes().add(avaliacaoSalva);
        cliente.getAvaliacoes().add(avaliacaoSalva);
        agendamento.setAvaliacaoDoCliente(avaliacaoSalva);

        barbeiroRepository.save(barbeiro);
        clienteRepository.save(cliente);
        agendamentoRepository.save(agendamento);

        return new AvaliacaoDTO(avaliacaoSalva);
    }

    public AvaliacaoDTO atualizar(Long id, AvaliacaoDTO avaliacaoDTO){
        if(!ValidacaoDeDados.avaliacaoValido(avaliacaoDTO, true)) throw new RuntimeException();

        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow();

        if(avaliacaoDTO.getNota() != null) avaliacao.setNota(avaliacaoDTO.getNota());
        if(avaliacaoDTO.getComentarios() != null) avaliacao.setComentarios(avaliacaoDTO.getComentarios());

        return new AvaliacaoDTO(avaliacaoRepository.save(avaliacao));
    }

    public void deletar(Long id){
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow();
        avaliacao.getAgendamento().setAvaliacaoDoCliente(null);
        avaliacaoRepository.delete(avaliacao);
    }

}
