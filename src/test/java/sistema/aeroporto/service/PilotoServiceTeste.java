package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sistema.aeroporto.dto.PilotoDTO;
import sistema.aeroporto.model.Piloto;
import sistema.aeroporto.repository.PilotoRepository;

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

        List<Piloto> resultado = pilotoService.listarTodosPilotos();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveBuscarPorCpfExistente() {

        Piloto p = new Piloto();
        p.setCpf("123");

        when(pilotoRepository.findByCpf("123")).thenReturn(Optional.of(p));

        Piloto resultado = pilotoService.buscarPorCpf("123");

        assertNotNull(resultado);
        assertEquals("123", resultado.getCpf());
    }

    @Test
    void deveLancarExcecaoQuandoCpfNaoExiste() {

        when(pilotoRepository.findByCpf("999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pilotoService.buscarPorCpf("999"));

        assertEquals("Piloto não encontrado", exception.getMessage());
    }

    @Test
    void deveSalvarPilotoGerandoMatricula() {

        PilotoDTO dto = new PilotoDTO(
                "Teste",
                "35",
                "1",
                "11144477735",
                "ATPL",
                null,
                "ATIVO");

        Piloto salvo = new Piloto();
        salvo.setId(10L);
        salvo.setMatricula("PIL123");

        when(pilotoRepository.save(any(Piloto.class))).thenReturn(salvo);

        Piloto resultado = pilotoService.salvarPiloto(dto);

        assertNotNull(resultado);
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

        PilotoDTO atualizado = new PilotoDTO(
                "Carlos",
                "40",
                "1",
                "11144477735",
                "ATPL",
                "MAT123",
                "ATIVO");

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(pilotoRepository.save(any(Piloto.class))).thenReturn(existente);

        Piloto result = pilotoService.atualizarPiloto(1L, atualizado);

        assertNotNull(result);
    }

    @Test
    void deveLancarExcecaoAoAtualizarPilotoInexistente() {

        PilotoDTO atualizado = new PilotoDTO(
                "Carlos",
                "40",
                "1",
                "11144477735",
                "ATPL",
                "MAT123",
                "ATIVO");

        when(pilotoRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pilotoService.atualizarPiloto(2L, atualizado));

        assertEquals("Piloto não encontrado", exception.getMessage());
    }
}
