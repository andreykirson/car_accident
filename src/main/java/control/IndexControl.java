package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentJdbcTemplate;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Controller
public class IndexControl {

    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "index";
    }
}