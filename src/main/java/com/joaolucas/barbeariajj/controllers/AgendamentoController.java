package com.joaolucas.barbeariajj.controllers;

import com.joaolucas.barbeariajj.models.dto.AgendamentoDTO;
import com.joaolucas.barbeariajj.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@RestController
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> encontrarTodos(){
        return ResponseEntity.ok(agendamentoService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> atualizar(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO){
        return ResponseEntity.ok(agendamentoService.atualizar(id, agendamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        agendamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
