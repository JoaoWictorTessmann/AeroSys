package sistema.aeroporto.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sistema.aeroporto.service.PilotoService;

@Controller
@RequestMapping("/pilotos")
public class PilotoViewController {

    @Autowired
    private PilotoService pilotoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pilotos", pilotoService.listarTodosPilotos());
        return "piloto/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("piloto", pilotoService.buscarPorId(id));
        return "piloto/detalhe";
    }

    @GetMapping("/novo")
    public String formularioNovo() {
        return "piloto/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("piloto", pilotoService.buscarPorId(id));
        return "piloto/formulario";
    }
}