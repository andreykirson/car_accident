package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentMem;
import service.AccidentService;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Controller
public class IndexControl {

//    private final AccidentService accidentService;
//
//    public IndexControl(AccidentService accidentService) {
//        this.accidentService = accidentService;
//    }
//
//
//    @GetMapping("/")
//    public String index(Model model) {
//        AccidentService accidentService = new AccidentService(new AccidentMem());
//        model.addAttribute("accidents", accidentService.getAll());
//        return "index";
//    }
}