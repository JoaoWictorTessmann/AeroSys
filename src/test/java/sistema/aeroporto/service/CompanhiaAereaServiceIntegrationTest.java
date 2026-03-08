package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import sistema.aeroporto.dto.CompanhiaAereaDTO;
import sistema.aeroporto.model.CompanhiaAerea;
import sistema.aeroporto.model.enums.CompanhiaAereaStatus;

@SpringBootTest
@Transactional
public class CompanhiaAereaServiceIntegrationTest {

        @Autowired
        private CompanhiaAereaService companhiaAereaService;

        @Test
        @DisplayName("Deve listar todas as companhias do banco")
        void deveListarTodasCompanhias() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                CompanhiaAereaDTO companhiaAereaGoldDto = new CompanhiaAereaDTO(
                                null, "Gol", "81.797.711/0001-30", true, "Ativa");

                companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);
                companhiaAereaService.salvarCompanhia(companhiaAereaGoldDto);

                // act
                List<CompanhiaAerea> listaDeCompanhias = companhiaAereaService.listarTodasCompanhias();

                // assert
                assertEquals(2, listaDeCompanhias.size());
                assertEquals("Azul", listaDeCompanhias.get(0).getNome());
                assertEquals("Gol", listaDeCompanhias.get(1).getNome());

        }

        @Test
        @DisplayName("Deve buscar companhia por nome")
        void deveBuscarCompanhiaPorNome() {

                // Arrange
                CompanhiaAereaDTO companhiaAereaAzulDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");

                companhiaAereaService.salvarCompanhia(companhiaAereaAzulDto);

                // Act
                CompanhiaAerea companhiaEncontrada = companhiaAereaService.buscarPorNome(companhiaAereaAzulDto.nome());

                // Assert
                assertEquals("Azul", companhiaEncontrada.getNome());
        }

        @Test
        @DisplayName("Deve falhar ao buscar companhia por nome inexistente")
        void deveFalharAoBuscarCompanhiaPorNomeInexistente() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.buscarPorNome("Gol"));

                // assert
                assertEquals("Companhia não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve buscar companhia por CNPJ")
        void deveBuscarCompanhiaPorCnpj() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                // act
                CompanhiaAerea companhiaEncontrada = companhiaAereaService.buscarPorCnpj("63.141.461/0001-02");

                // assert
                assertEquals("63.141.461/0001-02", companhiaEncontrada.getCnpj());
        }

        @Test
        @DisplayName("Deve falhar ao buscar companhia por CNPJ inexistente")
        void deveFalharAoBuscarCompanhiaPorCnpjInexistente() {

                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.buscarPorCnpj("05.451.308/0002-77"));

                // assert
                assertEquals("Companhia não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve salvar companhia com CNPJ válido")
        void deveSalvarCompanhiaComCnpjValido() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "55.044.476/0001-16", true, "Ativa");

                // act
                CompanhiaAerea companhiaSalva = companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                // assert
                assertEquals("55.044.476/0001-16", companhiaSalva.getCnpj());
        }

        @Test
        @DisplayName("Deve falhar ao salvar companhia com CNPJ inválido")
        void deveFalharAoSalvarCompanhiaComCnpjInvalido() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-03", true, "Ativa");

                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto));

                // assert
                assertEquals("CNPJ inválido", exception.getMessage());
        }

        @Test
        @DisplayName("Deve falhar ao salvar companhia com CNPJ já existente")
        void deveFalharAoSalvarCompanhiaComCnpjJaExistente() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                CompanhiaAereaDTO companhiaAereaGolDto = new CompanhiaAereaDTO(
                                null, "Gol", "63.141.461/0001-02", true, "Ativa");

                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.salvarCompanhia(companhiaAereaGolDto));

                // assert
                assertEquals("CNPJ já cadastrado", exception.getMessage());
        }

        @Test
        @DisplayName("Deve deletar companhia")
        void deveDeletarCompanhia() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "Ativa");
                CompanhiaAerea companhiaSalva = companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                // act
                companhiaAereaService.deletarCompanhia(companhiaSalva.getId());

                // assert
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.buscarPorId(companhiaSalva.getId()));

                assertEquals("Companhia não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve falhar ao deletar companhia inexistente")
        void deveFalharAoDeletarCompanhiaInexistente() {
                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.deletarCompanhia(666L));

                // assert
                assertEquals("Companhia não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve atualizar companhia existente")
        void deveAtualizarCompanhiaExistente() {
                // arrange
                CompanhiaAereaDTO companhiaAereaAzuldDto = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "ATIVA");
                CompanhiaAerea companhiaSalva = companhiaAereaService.salvarCompanhia(companhiaAereaAzuldDto);

                CompanhiaAereaDTO companhiaAtualizacao = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "INATIVA");

                // act
                CompanhiaAerea result = companhiaAereaService.atualizarCompanhia(companhiaSalva.getId(),
                                companhiaAtualizacao);

                // assert
                assertEquals(companhiaSalva.getId(), result.getId());
                assertEquals("Azul", result.getNome());
                assertEquals("63.141.461/0001-02", result.getCnpj());
                assertEquals(CompanhiaAereaStatus.INATIVA, result.getStatus());
        }

        @Test
        @DisplayName("Deve falhar ao atualizar companhia inexistente")
        void deveFalharAoAtualizarCompanhiaInexistente() {
                // arrange
                CompanhiaAereaDTO companhiaAereaDTO = new CompanhiaAereaDTO(
                                null, "Azul", "63.141.461/0001-02", true, "INATIVA");

                // act
                RuntimeException exception = assertThrows(
                                RuntimeException.class,
                                () -> companhiaAereaService.atualizarCompanhia(666L, companhiaAereaDTO));

                // assert
                assertEquals("Companhia não encontrada", exception.getMessage());
        }
}