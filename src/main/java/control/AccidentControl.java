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
        String[] ruleIds = req.getParameterValues("rIds");
        String typeId = req.getParameter("type.id");
        List<Rule> rules = accidentService.getRulesByIds(ruleIds);
        AccidentType accidentType = accidentService.getAccidentTypeById(typeId);
        accidentService.saveAccident(accident, rules, accidentType);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model) {

        Collection<AccidentType> types = accidentService.getAllAccidentType();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidentService.getAllRule();
        model.addAttribute("rules", rules);

        Accident accident = accidentService.getAccident(id);
        System.out.println("In updating request accident id is :" + accident.getAccidentId());
        model.addAttribute("accident", accident);


        return "accident/update";
    }

}
