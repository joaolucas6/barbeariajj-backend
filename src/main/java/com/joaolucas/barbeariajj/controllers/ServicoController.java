package com.joaolucas.barbeariajj.controllers;

import com.joaolucas.barbeariajj.models.dto.ServicoDTO;
import com.joaolucas.barbeariajj.services.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/servicos")
@RestController
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> retornarTodos(){
        return ResponseEntity.ok(servicoService.retornarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> retornarPorId(Long id){
        return ResponseEntity.ok(servicoService.retornarServicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> criar(@RequestBody ServicoDTO servicoDTO){
        return ResponseEntity.ok(servicoService.criar(servicoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO){
        return ResponseEntity.ok(servicoService.atualizar(id, servicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        servicoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/barbeiros/{servicoId}/{barbeiroId}")
    public ResponseEntity<Void> adicionarBarbeiro(@PathVariable Long servicoId, @PathVariable Long barbeiroId){
        servicoService.adicionarBarbeiro(servicoId, barbeiroId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/barbeiros/{servicoId}/{barbeiroId}")
    public ResponseEntity<Void> removerBarbeiro(@PathVariable Long servicoId, @PathVariable Long barbeiroId){
        servicoService.removerBarbeiro(servicoId, barbeiroId);
        return ResponseEntity.ok().build();
    }
}
