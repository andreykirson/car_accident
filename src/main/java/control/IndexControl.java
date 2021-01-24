package control;

import model.Accident;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentHibernate;
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

    private final AccidentRepository accidents;

    public IndexControl(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> res = new ArrayList<>();
        accidents.findAll().forEach(res::add);
        model.addAttribute("accidents", res);
        return "index";
    }
}