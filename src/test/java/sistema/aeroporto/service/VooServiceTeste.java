package sistema.aeroporto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sistema.aeroporto.model.CompanhiaAerea;
import sistema.aeroporto.model.Piloto;
import sistema.aeroporto.model.Voo;
import sistema.aeroporto.model.enums.CompanhiaAereaStatus;
import sistema.aeroporto.model.enums.PilotoStatus;
import sistema.aeroporto.model.enums.VooStatus;
import sistema.aeroporto.repository.CompanhiaAereaRepository;
import sistema.aeroporto.repository.PilotoRepository;
import sistema.aeroporto.repository.VooRepository;

public class VooServiceTeste {

    @InjectMocks
    private VooService vooService;

    @Mock
    private VooRepository vooRepository;

    @Mock
    private PilotoRepository pilotoRepository;

    @Mock
    private CompanhiaAereaRepository companhiaAereaRepository;

    private Piloto pilotoAtivo;
    private CompanhiaAerea companhiaAtiva;
    private Voo voo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        pilotoAtivo = new Piloto();
        pilotoAtivo.setId(1L);
        pilotoAtivo.setStatus(PilotoStatus.ATIVO);

        companhiaAtiva = new CompanhiaAerea();
        companhiaAtiva.setId(10L);
        companhiaAtiva.setStatus(CompanhiaAereaStatus.ATIVA);

        voo = new Voo();
        voo.setId(5L);
        voo.setCodigo("VOO001");
        voo.setPiloto(pilotoAtivo);
        voo.setCompanhia(companhiaAtiva);
        voo.setOrigem("São Paulo");
        voo.setDestino("Rio de Janeiro");
        voo.setHorarioPartidaPrevisto(LocalDateTime.now().plusHours(2));
    }
    
    @Test
    void deveCriarVooComSucesso() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));
        when(vooRepository.findByPiloto_Id(1L)).thenReturn(List.of());
        when(companhiaAereaRepository.findById(10L)).thenReturn(Optional.of(companhiaAtiva));
        when(vooRepository.existsByCodigo("VOO001")).thenReturn(false);
        when(vooRepository.save(any(Voo.class))).thenAnswer(i -> i.getArgument(0));

        Voo criado = vooService.criarVoo(voo);

        assertEquals(VooStatus.AGENDADO, criado.getStatus());
    }

    @Test
    void deveLancarErroQuandoPilotoNaoExiste() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Piloto não encontrado", e.getMessage());
    }

    @Test
    void deveLancarErroQuandoPilotoInativo() {
        pilotoAtivo.setStatus(PilotoStatus.INATIVO);
        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Piloto não está apto para voar", e.getMessage());
    }

    @Test
    void deveLancarErroPorConflitoDeHorario() {
        Voo outro = new Voo();
        outro.setHorarioPartidaPrevisto(voo.getHorarioPartidaPrevisto());

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));
        when(vooRepository.findByPiloto_Id(1L)).thenReturn(List.of(outro));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Piloto já está escalado para outro voo nesse horário", e.getMessage());
    }

    @Test
    void deveLancarErroQuandoCompanhiaNaoExiste() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));
        when(vooRepository.findByPiloto_Id(1L)).thenReturn(List.of());
        when(companhiaAereaRepository.findById(10L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Companhia aérea não encontrada", e.getMessage());
    }

    @Test
    void deveLancarErroQuandoCompanhiaInativa() {
        companhiaAtiva.setStatus(CompanhiaAereaStatus.INATIVA);

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));
        when(vooRepository.findByPiloto_Id(1L)).thenReturn(List.of());
        when(companhiaAereaRepository.findById(10L)).thenReturn(Optional.of(companhiaAtiva));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Companhia não está ativa", e.getMessage());
    }

    @Test
    void deveLancarErroCodigoDuplicado() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));
        when(vooRepository.findByPiloto_Id(1L)).thenReturn(List.of());
        when(companhiaAereaRepository.findById(10L)).thenReturn(Optional.of(companhiaAtiva));
        when(vooRepository.existsByCodigo("VOO001")).thenReturn(true);

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Código de voo já existente", e.getMessage());
    }

    @Test
    void deveLancarErroOrigemIgualDestino() {
        voo.setOrigem("Rio");
        voo.setDestino("Rio");

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Origem e destino não podem ser iguais", e.getMessage());
    }

    @Test
    void deveLancarErroHorarioNoPassado() {
        voo.setHorarioPartidaPrevisto(LocalDateTime.now().minusHours(1));

        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoAtivo));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.criarVoo(voo));
        assertEquals("Horário de partida não pode ser no passado", e.getMessage());
    }

    @Test
    void deveIniciarVooComSucesso() {
        when(vooRepository.findById(5L)).thenReturn(Optional.of(voo));
        when(vooRepository.save(any(Voo.class))).thenAnswer(i -> i.getArgument(0));

        Voo iniciado = vooService.iniciarVoo(5L);

        assertEquals(VooStatus.EM_VOO, iniciado.getStatus());
        assertNotNull(iniciado.getHorarioPartidaReal());
    }

    @Test
    void deveLancarErroAoIniciarVooNaoEncontrado() {
        when(vooRepository.findById(5L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.iniciarVoo(5L));
        assertEquals("Voo não encontrado", e.getMessage());
    }

    @Test
    void deveLancarErroPilotoInativoAoIniciar() {
        pilotoAtivo.setStatus(PilotoStatus.INATIVO);
        voo.setPiloto(pilotoAtivo);

        when(vooRepository.findById(5L)).thenReturn(Optional.of(voo));

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.iniciarVoo(5L));
        assertEquals("Piloto não pode iniciar o voo", e.getMessage());
    }

    @Test
    void deveCancelarVooComSucesso() {
        when(vooRepository.findById(5L)).thenReturn(Optional.of(voo));
        when(vooRepository.save(any(Voo.class))).thenAnswer(i -> i.getArgument(0));

        Voo cancelado = vooService.cancelarVoo(5L, "Mau tempo");

        assertEquals(VooStatus.CANCELADO, cancelado.getStatus());
        assertEquals("Mau tempo", cancelado.getMotivoCancelamento());
    }

    @Test
    void deveLancarErroSemMotivoCancelamento() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.cancelarVoo(5L, ""));
        assertEquals("Motivo do cancelamento é obrigatório", e.getMessage());
    }

    @Test
    void deveLancarErroCancelamentoVooNaoEncontrado() {
        when(vooRepository.findById(5L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.cancelarVoo(5L, "Motivo"));
        assertEquals("Voo não encontrado", e.getMessage());
    }

    @Test
    void deveListarTodosVoos() {
        when(vooRepository.findAll()).thenReturn(List.of(new Voo(), new Voo()));

        List<Voo> lista = vooService.listarTodos();

        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarPorStatus() {
        when(vooRepository.findByStatus(VooStatus.AGENDADO))
                .thenReturn(List.of(voo));

        List<Voo> encontrados = vooService.buscarPorStatus(VooStatus.AGENDADO);

        assertEquals(1, encontrados.size());
    }

    @Test
    void deveBuscarPorPiloto() {
        when(vooRepository.findByPiloto_Id(1L))
                .thenReturn(List.of(voo));

        List<Voo> encontrados = vooService.buscarPorPiloto(1L);

        assertEquals(1, encontrados.size());
    }

    @Test
    void deveBuscarPorCompanhia() {
        when(vooRepository.findByCompanhia_Id(10L))
                .thenReturn(List.of(voo));

        List<Voo> encontrados = vooService.buscarPorCompanhia(10L);

        assertEquals(1, encontrados.size());
    }

    @Test
    void deveAtualizarVooComSucesso() {
        Voo atualizado = new Voo();
        atualizado.setHorarioPartidaPrevisto(LocalDateTime.now().plusHours(10));
        atualizado.setHorarioChegadaPrevisto(LocalDateTime.now().plusHours(12));
        atualizado.setStatus(VooStatus.CONCLUIDO);

        when(vooRepository.findById(5L)).thenReturn(Optional.of(voo));
        when(vooRepository.save(any(Voo.class))).thenAnswer(i -> i.getArgument(0));

        Voo retorno = vooService.atualizarVoo(5L, atualizado);

        assertEquals(VooStatus.CONCLUIDO, retorno.getStatus());
        assertEquals(atualizado.getHorarioPartidaPrevisto(), retorno.getHorarioPartidaPrevisto());
    }

    @Test
    void deveLancarErroAtualizarVooNaoEncontrado() {
        when(vooRepository.findById(5L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class, () -> vooService.atualizarVoo(5L, voo));
        assertEquals("Voo não encontrado", e.getMessage());
    }
}
