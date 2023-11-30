package com.joaolucas.barbeariajj.services;


import com.joaolucas.barbeariajj.models.dto.ServicoDTO;
import com.joaolucas.barbeariajj.models.entities.Barbeiro;
import com.joaolucas.barbeariajj.models.entities.Servico;
import com.joaolucas.barbeariajj.repositories.BarbeiroRepository;
import com.joaolucas.barbeariajj.repositories.ServicoRepository;
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
class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;
    @Mock
    private BarbeiroRepository barbeiroRepository;
    private ServicoService emTeste;
    private Servico servico;
    private Barbeiro barbeiro;

    @BeforeEach
    void setUp() {
        emTeste = new ServicoService(servicoRepository, barbeiroRepository);
        servico = new Servico();
    }

    @Test
    void deveRetornarTodosOsServicos() {
        when(servicoRepository.findAll()).thenReturn(List.of(servico));

        var resultado = emTeste.retornarTodos();

        assertThat(resultado).isEqualTo(List.of(new ServicoDTO(servico)));
    }

    @Test
    void deveRetornarServicoPorId() {
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        var resultado = emTeste.retornarServicoPorId(1L);

        assertThat(resultado).isEqualTo(new ServicoDTO(servico));
    }

    @Test
    void deveCriarServico() {
        when(servicoRepository.save(servico)).thenReturn(servico);

        ServicoDTO servicoDTO = new ServicoDTO(servico);

        var resultado = emTeste.criar(servicoDTO);

        assertThat(resultado).isEqualTo(servicoDTO);
    }

    @Test
    void deveAtualizarServico() {
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(servicoRepository.save(servico)).thenReturn(servico);

        ServicoDTO servicoDTO = new ServicoDTO(servico);
        servicoDTO.setNome("low fade");

        var resultado = emTeste.atualizar(1L, servicoDTO);

        assertThat(servico.getNome()).isEqualTo("low fade");
    }

    @Test
    void deveDeletarServico() {
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        emTeste.deletar(1L);
        verify(servicoRepository, times(1)).delete(servico);
    }

    @Test
    void deveAdicionarBarbeiro() {
        barbeiro = new Barbeiro();
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));

        emTeste.adicionarBarbeiro(1L, 1L);

        assertThat(barbeiro.getEspecialidades().contains(servico) && servico.getBarbeirosEspecializados().contains(barbeiro)).isTrue();
    }

    @Test
    void deveRemoverBarbeiro() {
        barbeiro = new Barbeiro();
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(barbeiroRepository.findById(1L)).thenReturn(Optional.of(barbeiro));

        barbeiro.getEspecialidades().add(servico);
        servico.getBarbeirosEspecializados().add(barbeiro);

        emTeste.removerBarbeiro(1L, 1L);

        assertThat(!barbeiro.getEspecialidades().contains(servico) && !servico.getBarbeirosEspecializados().contains(barbeiro)).isTrue();
    }
}