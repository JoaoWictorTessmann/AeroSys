package sistema.aeroporto.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sistema.aeroporto.service.VooService;

@Controller
public class HomeViewController {

    @Autowired
    private VooService vooService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("voos", vooService.listarTodos());
        return "index";
    }
}