package control;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.AccidentService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {

        Collection<AccidentType> types = accidentService.getAllAccidentType();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidentService.getAllRule();
        model.addAttribute("rules", rules);

        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        int ruleId = Integer.parseInt(req.getParameter("rIds"));
        int typeId = Integer.parseInt(req.getParameter("type.id"));
        Rule rule = accidentService.getRuleById(ruleId);
        AccidentType type = accidentService.getAccidentTypeById(typeId);
        accidentService.saveAccident(accident, type, rule);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.getAccident(id));
        return "accident/update";
    }

}
