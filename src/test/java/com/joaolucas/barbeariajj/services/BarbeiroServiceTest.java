package com.joaolucas.barbeariajj.services;

import com.joaolucas.barbeariajj.models.dto.BarbeiroDTO;
import com.joaolucas.barbeariajj.models.dto.ClienteDTO;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.Cliente;
import com.joaolucas.barbeariajj.repositories.BarbeiroRepository;
import com.joaolucas.barbeariajj.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarbeiroServiceTest {
    private BarbeiroService emTeste;
    @Mock
    private BarbeiroRepository barbeiroRepository;
    private Barbeiro barbeiro;

    @BeforeEach
    void setUp() {
        emTeste = new BarbeiroService(barbeiroRepository);
        barbeiro = new Barbeiro();
    }

    @Test
    void deveRetornarTodosOsBarbeiros() {
        when(barbeiroRepository.findAll()).thenReturn(List.of(barbeiro));
        var resultado = emTeste.encontrarTodos();
        assertThat(resultado).isEqualTo(List.of(new BarbeiroDTO(barbeiro)));
    }

    @Test
    void deveRetornarBabeiroPorId() {
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));
        var resultado = emTeste.encontrarPorId(1L);
        assertThat(resultado).isEqualTo(new BarbeiroDTO(barbeiro));
    }

    @Test
    void deveAtualizarBarbeiro() {
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));
        when(barbeiroRepository.save(barbeiro)).thenReturn(barbeiro);

        BarbeiroDTO barbeiroDTO = new BarbeiroDTO(barbeiro);
        barbeiroDTO.setNome("eu quero chorar");

        var resultado = emTeste.atualizar(1L, barbeiroDTO);

        assertThat(resultado).isNotNull();
        assertThat(barbeiro.getNome()).isEqualTo("eu quero chorar");
    }

    @Test
    void deveDeletarBarbeiro() {
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));
        emTeste.deletar(1L);
        verify(barbeiroRepository, times(1)).delete(barbeiro);
    }
}