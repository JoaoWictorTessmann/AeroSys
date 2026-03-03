package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sistema.aeroporto.model.Piloto;
import sistema.aeroporto.repository.PilotoRepository;
import sistema.aeroporto.model.enums.PilotoStatus;

public class PilotoServiceTeste {

    @Mock
    private PilotoRepository pilotoRepository;

    @InjectMocks
    private PilotoService pilotoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosPilotos() {
        when(pilotoRepository.findAll()).thenReturn(Arrays.asList(new Piloto(), new Piloto()));
        assertEquals(2, pilotoService.listarTodosPilotos().size());
    }

    @Test
    void deveBuscarPorCpfExistente() {
        Piloto p = new Piloto();
        p.setCpf("123");

        when(pilotoRepository.findByCpf("123")).thenReturn(Optional.of(p));

        assertNotNull(pilotoService.buscarPorCpf("123"));
    }

    @Test
    void deveRetornarNullQuandoCpfNaoExiste() {
        when(pilotoRepository.findByCpf("999")).thenReturn(Optional.empty());
        assertNull(pilotoService.buscarPorCpf("999"));
    }

    @Test
    void deveSalvarPilotoGerandoMatricula() {
        Piloto p = new Piloto();
        p.setNome("Teste");
        p.setCpf("11144477735");

        Piloto salvo = new Piloto();
        salvo.setId(10L);

        when(pilotoRepository.save(any())).thenReturn(salvo);

        Piloto resultado = pilotoService.salvarPiloto(p);

        assertTrue(resultado.getMatricula().startsWith("PIL"));
    }

    @Test
    void deveDeletarPiloto() {
        doNothing().when(pilotoRepository).deleteById(1L);
        pilotoService.deletarPiloto(1L);
        verify(pilotoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizarPiloto() {
        Piloto existente = new Piloto();
        existente.setId(1L);

        Piloto atualizado = new Piloto();
        atualizado.setStatus(PilotoStatus.ATIVO);
        atualizado.setDataRenovacao(LocalDate.now());

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(pilotoRepository.save(existente)).thenReturn(existente);

        Piloto result = pilotoService.atualizarPiloto(1L, atualizado);

        assertEquals(atualizado.getStatus(), result.getStatus());
        assertEquals(atualizado.getDataRenovacao(), result.getDataRenovacao());
    }

    @Test
    void deveRetornarNullAoAtualizarPilotoInexistente() {
        when(pilotoRepository.findById(2L)).thenReturn(Optional.empty());
        assertNull(pilotoService.atualizarPiloto(2L, new Piloto()));
    }
}
