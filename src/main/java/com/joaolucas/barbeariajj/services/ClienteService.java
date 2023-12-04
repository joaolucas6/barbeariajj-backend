package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.exceptions.BadRequestException;
import com.joaolucas.barbeariajj.exceptions.ResourceNotFoundException;
import com.joaolucas.barbeariajj.models.dto.ClienteDTO;
import com.joaolucas.barbeariajj.models.entities.Cliente;
import com.joaolucas.barbeariajj.repositories.ClienteRepository;
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteDTO> encontrarTodos(){
        return clienteRepository.findAll().stream().map(ClienteDTO::new).toList();
    }

    public ClienteDTO encontrarPorId(Long id){
        return new ClienteDTO(clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não foi encontrado com ID: " + id)));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO){
        if(!ValidacaoDeDados.userValido(clienteDTO)) throw new BadRequestException("Dados do cliente são inválidos");

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não foi encontrado com ID: " + id));

        if(clienteDTO.getNome() != null) cliente.setNome(clienteDTO.getNome());
        if(clienteDTO.getSobrenome() != null) cliente.setSobrenome(clienteDTO.getSobrenome());
        if(clienteDTO.getEmail() != null) cliente.setEmail(clienteDTO.getEmail());
        if(clienteDTO.getNumeroTelefone() != null) cliente.setNumeroTelefone(clienteDTO.getNumeroTelefone());
        if(clienteDTO.getGenero() != null) cliente.setGenero(clienteDTO.getGenero());
        if(clienteDTO.getCpf() != null) cliente.setCpf(clienteDTO.getCpf());

        return new ClienteDTO(clienteRepository.save(cliente));
    }

    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não foi encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }
}
