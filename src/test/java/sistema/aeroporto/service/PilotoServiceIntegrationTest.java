package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import sistema.aeroporto.dto.PilotoDTO;
import sistema.aeroporto.model.Piloto;

@SpringBootTest
@Transactional
public class PilotoServiceIntegrationTest {

    @Autowired
    private PilotoService pilotoService;

    @Test
    @DisplayName("Deve listar todos os pilotos do banco")
    void deveListarTodosPilotos() {
        // arrange
        PilotoDTO piloto = new PilotoDTO(
                "João Silva",
                "35",
                "1",
                "549.909.720-82",
                "ATPL",
                "MAT123",
                "ATIVO");

        PilotoDTO piloto2 = new PilotoDTO(
                "Maria Silva",
                "25",
                "2",
                "557.271.330-92",
                "ATPL2",
                "MAT125",
                "ATIVO");

        pilotoService.salvarPiloto(piloto);
        pilotoService.salvarPiloto(piloto2);

        // act
        List<Piloto> listaDePilotos = pilotoService.listarTodosPilotos();

        // assert
        assertEquals(2, listaDePilotos.size());
        assertEquals("João Silva", listaDePilotos.get(0).getNome());
        assertEquals("Maria Silva", listaDePilotos.get(1).getNome());
    }

    @Test
    @DisplayName("Deve buscar piloto por CPF existente")
    void deveBuscarPorCpfExistente() {

        // arrange
        PilotoDTO piloto = new PilotoDTO(
                "João",
                "40",
                "1",
                "111.444.777-35",
                "ATPL",
                "MAT001",
                "ATIVO");

        pilotoService.salvarPiloto(piloto);

        // act
        Piloto pilotoEncontrado = pilotoService.buscarPorCpf("11144477735");

        // assert
        assertNotNull(pilotoEncontrado);
        assertEquals("João", pilotoEncontrado.getNome());
        assertEquals("11144477735", pilotoEncontrado.getCpf());
    }

    @Test
    @DisplayName("Deve retornar null ao buscar por CPF inexistente")
    void deveRetornarNullQuandoCpfNaoExiste() {

        // act
        RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> pilotoService.buscarPorCpf("99999999999"));

        // assert
        assertEquals("Piloto não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve salvar piloto gerando matrícula automaticamente")
    void deveSalvarPilotoGerandoMatricula() {

        // arrange
        PilotoDTO piloto = new PilotoDTO(
                "Carlos",
                "38",
                "1",
                "111.444.777-35",
                "ATPL",
                null,
                "ATIVO");

        // act
        Piloto pilotoSalvo = pilotoService.salvarPiloto(piloto);

        // assert
        assertNotNull(pilotoSalvo);

        assertEquals("Carlos", pilotoSalvo.getNome());
        assertEquals("11144477735", pilotoSalvo.getCpf());

        assertNotNull(pilotoSalvo.getMatricula());
        assertFalse(pilotoSalvo.getMatricula().isBlank());

        assertTrue(pilotoSalvo.getMatricula().startsWith("PIL"));
    }

    @Test
    @DisplayName("Deve deletar piloto")
    void deveDeletarPiloto() {

        // arrange
        PilotoDTO piloto = new PilotoDTO(
                "Carlos",
                "38",
                "1",
                "111.444.777-35",
                "ATPL",
                "MAT123",
                "ATIVO");

        Piloto pilotoSalvo = pilotoService.salvarPiloto(piloto);

        // act
        assertDoesNotThrow(() -> pilotoService.deletarPiloto(pilotoSalvo.getId()));

        // assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pilotoService.buscarPorId(pilotoSalvo.getId()));

        assertEquals("Piloto não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve atualizar piloto")
    void deveAtualizarPiloto() {

        // arrange
        PilotoDTO piloto = new PilotoDTO(
                "Carlos",
                "38",
                "1",
                "111.444.777-35",
                "ATPL",
                "MAT123",
                "ATIVO");

        Piloto pilotoSalvo = pilotoService.salvarPiloto(piloto);

        PilotoDTO pilotoAtualizado = new PilotoDTO(
                "Carlos Silva",
                "38",
                "1",
                "111.444.777-35",
                "ATPL",
                "MAT123",
                "ATIVO");

        // act
        Piloto pilotoEditado = pilotoService.atualizarPiloto(
                pilotoSalvo.getId(),
                pilotoAtualizado);

        // assert
        assertNotNull(pilotoEditado);
        assertEquals("Carlos Silva", pilotoEditado.getNome());
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar piloto inexistente")
    void deveRetornarNullAoAtualizarPilotoInexistente() {

        // arrange
        PilotoDTO pilotoAtualizado = new PilotoDTO(
                "Carlos Silva",
                "38",
                "1",
                "111.444.777-35",
                "ATPL",
                "MAT123",
                "ATIVO");

        // act
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> pilotoService.atualizarPiloto(666L, pilotoAtualizado));

        // assert
        assertEquals("Piloto não encontrado", exception.getMessage());
    }
}