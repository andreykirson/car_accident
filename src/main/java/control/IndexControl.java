package control;

import model.Accident;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentRepository;

/**
 * @author Andrey
 * @version 1
 * @since 23/01/21
 */

@Controller
public class IndexControl {

    private final AccidentRepository accidentRepository;

    public IndexControl(AccidentRepository store) {
        this.accidentRepository = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentRepository.findAll());
        return "index";
    }
}