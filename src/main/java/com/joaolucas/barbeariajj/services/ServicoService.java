package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.exceptions.BadRequestException;
import com.joaolucas.barbeariajj.exceptions.BusinessLogicException;
import com.joaolucas.barbeariajj.exceptions.ResourceNotFoundException;
import com.joaolucas.barbeariajj.models.dto.ServicoDTO;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.Servico;
import com.joaolucas.barbeariajj.repositories.BarbeiroRepository;
import com.joaolucas.barbeariajj.repositories.ServicoRepository;
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final BarbeiroRepository barbeiroRepository;

    public List<ServicoDTO> retornarTodos(){
        return servicoRepository.findAll().stream().map(ServicoDTO::new).toList();
    }

    public ServicoDTO retornarServicoPorId(Long id){
        return new ServicoDTO(servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não foi encontrado com ID: " + id)));
    }

    public ServicoDTO criar(ServicoDTO servicoDTO){
        if(!ValidacaoDeDados.servicoValido(servicoDTO)) throw new BadRequestException("Dados do serviço inválidos");

        Servico servico = new Servico();
        servico.setNome(servicoDTO.getNome());
        servico.setDescricao(servicoDTO.getDescricao());
        servico.setPreco(servicoDTO.getPreco());

        return new ServicoDTO(servicoRepository.save(servico));
    }

    public ServicoDTO atualizar(Long id, ServicoDTO servicoDTO){
        if(!ValidacaoDeDados.servicoValido(servicoDTO)) throw new BadRequestException("Dados do serviço inválidos");

        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não foi encontrado com ID: " + id));

        if(servicoDTO.getNome() != null) servico.setNome(servicoDTO.getNome());
        if(servicoDTO.getDescricao() != null) servico.setDescricao(servicoDTO.getDescricao());
        if(servicoDTO.getPreco() != null) servico.setPreco(servicoDTO.getPreco());

        return new ServicoDTO(servicoRepository.save(servico));
    }

    public void deletar(Long id){
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Serviço não foi encontrado com ID: " + id));
        servicoRepository.delete(servico);
    }

    public void adicionarBarbeiro(Long servicoId, Long barbeiroId){
        Servico servico = servicoRepository.findById(servicoId).orElseThrow(() -> new ResourceNotFoundException("Serviço não foi encontrado com ID: " + servicoId));
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId).orElseThrow(() -> new ResourceNotFoundException("Barbeiro não foi encontrado com ID: " + barbeiroId));

        if(servico.getBarbeirosEspecializados().contains(barbeiro) || barbeiro.getEspecialidades().contains(servico)) throw new BusinessLogicException("Barbeiro já está incluído na lista");

        servico.getBarbeirosEspecializados().add(barbeiro);
        barbeiro.getEspecialidades().add(servico);

        servicoRepository.save(servico);
        barbeiroRepository.save(barbeiro);
    }

    public void removerBarbeiro(Long servicoId, Long barbeiroId){
        Servico servico = servicoRepository.findById(servicoId).orElseThrow(() -> new ResourceNotFoundException("Serviço não foi encontrado com ID: " + servicoId));
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId).orElseThrow(() -> new ResourceNotFoundException("Barbeiro não foi encontrado com ID: " + barbeiroId));

        if(!servico.getBarbeirosEspecializados().contains(barbeiro) || !barbeiro.getEspecialidades().contains(servico)) throw new BusinessLogicException("Barbeiro não está incluído na lista");

        servico.getBarbeirosEspecializados().remove(barbeiro);
        barbeiro.getEspecialidades().remove(servico);

        servicoRepository.save(servico);
        barbeiroRepository.save(barbeiro);
    }

}
