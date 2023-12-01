package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.BarbeiroDTO;
import com.joaolucas.barbeariajj.models.dto.ClienteDTO;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.Cliente;
import com.joaolucas.barbeariajj.repositories.BarbeiroRepository;
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public List<BarbeiroDTO> encontrarTodos(){
        return barbeiroRepository.findAll().stream().map(BarbeiroDTO::new).toList();
    }

    public BarbeiroDTO encontrarPorId(Long id){
        return new BarbeiroDTO(barbeiroRepository.findById(id).orElseThrow());
    }

    public BarbeiroDTO atualizar(Long id, BarbeiroDTO barbeiroDTO){
        if(!ValidacaoDeDados.userValido(barbeiroDTO)) throw new RuntimeException();

        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();

        if(barbeiroDTO.getNome() != null) barbeiro.setNome(barbeiroDTO.getNome());
        if(barbeiroDTO.getSobrenome() != null) barbeiro.setSobrenome(barbeiroDTO.getSobrenome());
        if(barbeiroDTO.getEmail() != null) barbeiro.setEmail(barbeiroDTO.getEmail());
        if(barbeiroDTO.getNumeroTelefone() != null) barbeiro.setNumeroTelefone(barbeiroDTO.getNumeroTelefone());
        if(barbeiroDTO.getGenero() != null) barbeiro.setGenero(barbeiroDTO.getGenero());
        if(barbeiroDTO.getCpf() != null) barbeiro.setCpf(barbeiroDTO.getCpf());

        return new BarbeiroDTO(barbeiroRepository.save(barbeiro));
    }

    public void deletar(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();
        barbeiroRepository.delete(barbeiro);
    }
}
