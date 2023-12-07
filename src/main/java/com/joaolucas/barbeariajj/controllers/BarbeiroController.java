package com.joaolucas.barbeariajj.controllers;

import com.joaolucas.barbeariajj.models.dto.BarbeiroDTO;
import com.joaolucas.barbeariajj.services.BarbeiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @GetMapping
    public ResponseEntity<List<BarbeiroDTO>> encontrarTodos(){
        return ResponseEntity.ok(barbeiroService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(barbeiroService.encontrarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> atualizar(@PathVariable Long id, @RequestBody BarbeiroDTO barbeiroDTO){
        return ResponseEntity.ok(barbeiroService.atualizar(id, barbeiroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        barbeiroService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
