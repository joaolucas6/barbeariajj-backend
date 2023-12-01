package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.AdminDTO;
import com.joaolucas.barbeariajj.models.entities.Admin;
import com.joaolucas.barbeariajj.repositories.AdminRepository;
import com.joaolucas.barbeariajj.utils.ValidacaoDeDados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<AdminDTO> encontrarTodos(){
        return adminRepository.findAll().stream().map(AdminDTO::new).toList();
    }

    public AdminDTO encontrarPorId(Long id){
        return new AdminDTO(adminRepository.findById(id).orElseThrow());
    }

    public AdminDTO atualizar(Long id, AdminDTO adminDTO){
        if(!ValidacaoDeDados.userValido(adminDTO)) throw new RuntimeException();

        Admin admin = adminRepository.findById(id).orElseThrow();

        if(adminDTO.getNome() != null) admin.setNome(adminDTO.getNome());
        if(adminDTO.getSobrenome() != null) admin.setSobrenome(adminDTO.getSobrenome());
        if(adminDTO.getEmail() != null) admin.setEmail(adminDTO.getEmail());
        if(adminDTO.getNumeroTelefone() != null) admin.setNumeroTelefone(adminDTO.getNumeroTelefone());
        if(adminDTO.getGenero() != null) admin.setGenero(adminDTO.getGenero());
        if(adminDTO.getCpf() != null) admin.setCpf(adminDTO.getCpf());

        return new AdminDTO(adminRepository.save(admin));
    }

    public void deletar(Long id){
        Admin admin = adminRepository.findById(id).orElseThrow();
        adminRepository.delete(admin);
    }
}