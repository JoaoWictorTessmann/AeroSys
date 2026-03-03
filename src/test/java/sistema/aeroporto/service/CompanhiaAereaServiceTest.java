package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import sistema.aeroporto.model.CompanhiaAerea;
import sistema.aeroporto.model.enums.CompanhiaAereaStatus;
import sistema.aeroporto.repository.CompanhiaAereaRepository;
import sistema.aeroporto.util.CnpjUtils;

public class CompanhiaAereaServiceTest {

    @InjectMocks
    private CompanhiaAereaService companhiaService;

    @Mock
    private CompanhiaAereaRepository companhiaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodasCompanhias() {
        when(companhiaRepository.findAll()).thenReturn(List.of(new CompanhiaAerea(), new CompanhiaAerea()));

        List<CompanhiaAerea> lista = companhiaService.listarTodasCompanhias();

        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarCompanhiaPorNome() {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setNome("Azul");

        when(companhiaRepository.findByNome("Azul")).thenReturn(Optional.of(c));

        CompanhiaAerea result = companhiaService.buscarPorNome("Azul");

        assertEquals("Azul", result.getNome());
    }

    @Test
    void deveFalharAoBuscarCompanhiaPorNomeInexistente() {
        when(companhiaRepository.findByNome("X")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            companhiaService.buscarPorNome("X");
        });

        assertEquals("Companhia não encontrada", ex.getMessage());
    }

    @Test
    void deveBuscarCompanhiaPorCnpj() {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setCnpj("12345678000199");

        when(companhiaRepository.findByCnpj("12345678000199")).thenReturn(Optional.of(c));

        CompanhiaAerea result = companhiaService.buscarPorCnpj("12345678000199");

        assertEquals("12345678000199", result.getCnpj());
    }

    @Test
    void deveFalharAoBuscarCompanhiaPorCnpjInexistente() {
        when(companhiaRepository.findByCnpj("000")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            companhiaService.buscarPorCnpj("000");
        });

        assertEquals("Companhia não encontrada", ex.getMessage());
    }

    @Test
    void deveSalvarCompanhiaComCnpjValido() {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setCnpj("40510225000102");

        try (MockedStatic<CnpjUtils> mock = mockStatic(CnpjUtils.class)) {
            mock.when(() -> CnpjUtils.validarCnpj("40510225000102")).thenReturn(true);

            when(companhiaRepository.existsByCnpj("40510225000102")).thenReturn(false);
            when(companhiaRepository.save(c)).thenReturn(c);

            CompanhiaAerea result = companhiaService.salvarCompanhia(c);

            assertEquals("40510225000102", result.getCnpj());
        }
    }

    @Test
    void deveFalharAoSalvarCompanhiaComCnpjInvalido() {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setCnpj("000");

        try (MockedStatic<CnpjUtils> mock = mockStatic(CnpjUtils.class)) {
            mock.when(() -> CnpjUtils.validarCnpj("000")).thenReturn(false);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> {
                companhiaService.salvarCompanhia(c);
            });

            assertEquals("CNPJ inválido", ex.getMessage());
        }
    }

    @Test
    void deveFalharAoSalvarCompanhiaComCnpjDuplicado() {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setCnpj("40510225000102");

        try (MockedStatic<CnpjUtils> mock = mockStatic(CnpjUtils.class)) {
            mock.when(() -> CnpjUtils.validarCnpj("40510225000102")).thenReturn(true);
            when(companhiaRepository.existsByCnpj("40510225000102")).thenReturn(true);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> {
                companhiaService.salvarCompanhia(c);
            });

            assertEquals("CNPJ já cadastrado", ex.getMessage());
        }
    }

    @Test
    void deveDeletarCompanhia() {
        when(companhiaRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> companhiaService.deletarCompanhia(1L));

        verify(companhiaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveFalharAoDeletarCompanhiaInexistente() {
        when(companhiaRepository.existsById(1L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            companhiaService.deletarCompanhia(1L);
        });

        assertEquals("Companhia não encontrada", ex.getMessage());
    }

    @Test
    void deveAtualizarCompanhia() {
        CompanhiaAerea existente = new CompanhiaAerea();
        existente.setId(1L);
        existente.setStatus(CompanhiaAereaStatus.ATIVA);

        CompanhiaAerea atualizada = new CompanhiaAerea();
        atualizada.setStatus(CompanhiaAereaStatus.INATIVA);

        when(companhiaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(companhiaRepository.save(existente)).thenReturn(existente);

        CompanhiaAerea result = companhiaService.atualizarCompanhia(1L, atualizada);

        assertEquals(CompanhiaAereaStatus.INATIVA, result.getStatus());
    }

    @Test
    void deveFalharAoAtualizarCompanhiaInexistente() {
        when(companhiaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            companhiaService.atualizarCompanhia(1L, new CompanhiaAerea());
        });

        assertEquals("Companhia não encontrada", ex.getMessage());
    }
}
