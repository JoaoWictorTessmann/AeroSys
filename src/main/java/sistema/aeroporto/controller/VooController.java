package sistema.aeroporto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sistema.aeroporto.dto.VooDTO;
import sistema.aeroporto.model.Voo;
import sistema.aeroporto.service.VooService;

@RestController
@RequestMapping("/voos")
public class VooController {

    @Autowired
    private VooService vooService;

    // Criar voo
    @PostMapping
    public ResponseEntity<Voo> criarVoo(@RequestBody VooDTO voo) {
        Voo novoVoo = vooService.criarVoo(voo);
        return ResponseEntity.ok(novoVoo);
    }

    // Listar todos os voos
    @GetMapping
    public ResponseEntity<List<Voo>> listarTodos() {
        return ResponseEntity.ok(vooService.listarTodos());
    }

    //buscar voo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Voo> buscarPorId(@PathVariable Long id) {
        Voo voo = vooService.buscarPorId(id);
        return ResponseEntity.ok(voo);
    }

    // Buscar voos por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Voo>> buscarPorStatus(@PathVariable VooDTO status) {
        return ResponseEntity.ok(vooService.buscarPorStatus(status));
    }

    // Buscar voos por piloto
    @GetMapping("/piloto/{pilotoId}")
    public ResponseEntity<List<Voo>> buscarPorPiloto(@PathVariable Long pilotoId) {
        return ResponseEntity.ok(vooService.buscarPorPiloto(pilotoId));
    }

    // Buscar voos por companhia
    @GetMapping("/companhia/{companhiaId}")
    public ResponseEntity<List<Voo>> buscarPorCompanhia(@PathVariable Long companhiaId) {
        return ResponseEntity.ok(vooService.buscarPorCompanhia(companhiaId));
    }

    // Iniciar voo
    @PutMapping("/iniciar/{vooId}")
    public ResponseEntity<Voo> iniciarVoo(@PathVariable Long vooId) {
        Voo voo = vooService.iniciarVoo(vooId);
        return ResponseEntity.ok(voo);
    }

    // Cancelar voo com motivo
    @PutMapping("/cancelar/{vooId}")
    public ResponseEntity<Voo> cancelarVoo(@PathVariable Long vooId, @RequestParam VooDTO motivo) {
        Voo voo = vooService.cancelarVoo(vooId, motivo);
        return ResponseEntity.ok(voo);
    }

    // Atualizar voo
    @PutMapping("/atualizar/{vooId}")
    public ResponseEntity<Voo> atualizarVoo(@PathVariable Long vooId, @RequestBody VooDTO vooAtualizado) {
        Voo voo = vooService.atualizarVoo(vooId, vooAtualizado);
        return ResponseEntity.ok(voo);
    }
}
