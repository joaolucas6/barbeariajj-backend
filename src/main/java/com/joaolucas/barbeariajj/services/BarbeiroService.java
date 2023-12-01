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

    public BarbeiroDTO atualizar(Long id, ClienteDTO clienteDTO){
        if(!ValidacaoDeDados.userValido(clienteDTO)) throw new RuntimeException();

        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();

        if(clienteDTO.getNome() != null) barbeiro.setNome(clienteDTO.getNome());
        if(clienteDTO.getSobrenome() != null) barbeiro.setSobrenome(clienteDTO.getSobrenome());
        if(clienteDTO.getEmail() != null) barbeiro.setEmail(clienteDTO.getEmail());
        if(clienteDTO.getNumeroTelefone() != null) barbeiro.setNumeroTelefone(clienteDTO.getNumeroTelefone());
        if(clienteDTO.getGenero() != null) barbeiro.setGenero(clienteDTO.getGenero());
        if(clienteDTO.getCpf() != null) barbeiro.setCpf(clienteDTO.getCpf());

        return new BarbeiroDTO(barbeiroRepository.save(barbeiro));
    }

    public void deletar(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id).orElseThrow();
        barbeiroRepository.delete(barbeiro);
    }
}
