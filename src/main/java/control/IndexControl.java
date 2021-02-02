package control;

import model.Accident;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @since 23/01/21
 */

@Controller
public class IndexControl {

    private final AccidentRepository store;

    public IndexControl(AccidentRepository store) {
        this.store = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", store.findAll());
        return "index";
    }
}