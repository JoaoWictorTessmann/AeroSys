package sistema.aeroporto.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sistema.aeroporto.service.CompanhiaAereaService;

@Controller
@RequestMapping("/companhias")
public class CompanhiaAereaViewController {

    @Autowired
    private CompanhiaAereaService companhiaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("companhias", companhiaService.listarTodasCompanhias());
        return "companhia/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("companhia", companhiaService.buscarPorId(id));
        return "companhia/detalhe";
    }

    @GetMapping("/novo")
    public String formularioNovo() {
        return "companhia/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("companhia", companhiaService.buscarPorId(id));
        return "companhia/formulario";
    }
}