package control;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.HbmStore;

import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @since 23/01/21
 */

@Controller
public class IndexControl {

    private final HbmStore hbmStore;

    public IndexControl(HbmStore hbmStore) {
        this.hbmStore = hbmStore;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", hbmStore.getAllAccidents());
        return "index";
    }
}