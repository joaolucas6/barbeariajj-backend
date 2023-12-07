package com.joaolucas.barbeariajj.controllers;

import com.joaolucas.barbeariajj.models.dto.AvaliacaoDTO;
import com.joaolucas.barbeariajj.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> encontrarTodos(){
        return ResponseEntity.ok(avaliacaoService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(avaliacaoService.encontrarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> criar(@RequestBody AvaliacaoDTO avaliacaoDTO){
        return ResponseEntity.ok(avaliacaoService.criar(avaliacaoDTO.getAgendamentoId(), avaliacaoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable Long id, @RequestBody AvaliacaoDTO avaliacaoDTO){
        return ResponseEntity.ok(avaliacaoService.atualizar(id, avaliacaoDTO));
    }

    @DeleteMapping("/{avaliacaoId}")
    public ResponseEntity<AvaliacaoDTO> deletar(@PathVariable Long id){
        avaliacaoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
