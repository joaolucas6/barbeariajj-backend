package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AgendamentoDTO;
import com.joaolucas.barbeariajj.models.entities.Agendamento;
import com.joaolucas.barbeariajj.repositories.AgendamentoRepository;
import com.joaolucas.barbeariajj.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;

    public List<AgendamentoDTO> encontrarTodos(){
        return agendamentoRepository.findAll().stream().map(AgendamentoDTO::new).toList();
    }

    public AgendamentoDTO encontrarPorId(Long id){
        return new AgendamentoDTO(agendamentoRepository.findById(id).orElseThrow());
    }

    public AgendamentoDTO atualizar(Long id, AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow();

        if(agendamentoDTO.getServicosId() != null){
            agendamento.setServicos(agendamentoDTO.getServicosId().stream().map(i -> servicoRepository.findById(i).orElseThrow()).toList());
        }

        if(agendamentoDTO.getMetodoPagamento() != null) agendamento.setMetodoPagamento(agendamentoDTO.getMetodoPagamento());
        if(agendamentoDTO.getStatus() != null) agendamento.setStatus(agendamentoDTO.getStatus());
        if(agendamentoDTO.getExigenciasDoCliente() != null) agendamento.setExigenciasDoCliente(agendamentoDTO.getExigenciasDoCliente());
        if(agendamentoDTO.getPrecoAdicional() != null) agendamento.setPrecoAdicional(agendamentoDTO.getPrecoAdicional());
        if(agendamentoDTO.getPrecoDescontado() != null) agendamento.setPrecoDescontado(agendamentoDTO.getPrecoAdicional());

        return new AgendamentoDTO(agendamentoRepository.save(agendamento));
    }

    public void deletar(Long id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow();
        agendamentoRepository.delete(agendamento);
    }
}
