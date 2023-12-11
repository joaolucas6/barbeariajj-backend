package com.joaolucas.barbeariajj.controllers;

import com.joaolucas.barbeariajj.models.dto.PedidoAgendamentoDTO;
import com.joaolucas.barbeariajj.services.PedidoAgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos-agendamento")
@RequiredArgsConstructor
public class PedidoAgendamentoController {

    private final PedidoAgendamentoService pedidoAgendamentoService;

    @GetMapping
    public ResponseEntity<List<PedidoAgendamentoDTO>> encontrarTodos(){
        return ResponseEntity.ok(pedidoAgendamentoService.encontrarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAgendamentoDTO> encontrarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pedidoAgendamentoService.encontrarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoAgendamentoDTO> criar(@RequestBody PedidoAgendamentoDTO pedidoAgendamentoDTO){
        return ResponseEntity.ok(pedidoAgendamentoService.criar(pedidoAgendamentoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoAgendamentoDTO> atualizar(@PathVariable Long id, @RequestBody PedidoAgendamentoDTO pedidoAgendamentoDTO){
        return ResponseEntity.ok(pedidoAgendamentoService.atualizar(id, pedidoAgendamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pedidoAgendamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/aceitar-pedido/{id}")
    public ResponseEntity<Void> aceitarPedido(@PathVariable Long id){
        pedidoAgendamentoService.aceitarPedido(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/negar-pedido/{id}")
    public ResponseEntity<Void> negarPedido(@PathVariable Long id){
        pedidoAgendamentoService.negarPedido(id);
        return ResponseEntity.ok().build();
    }

}
