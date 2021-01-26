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
import repository.JDBCStore;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccidentControl {

    private final JDBCStore jdbcStore;

    public AccidentControl(JDBCStore jdbcStore) {
        this.jdbcStore = jdbcStore;
    }

    @GetMapping("/create")
    public String create(Model model) {

        List<AccidentType> types = jdbcStore.getAllAccidentTypes();
        model.addAttribute("types", types);

        List<Rule> rules = jdbcStore.getAllRules();
        model.addAttribute("rules", rules);

        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        int ruleId = Integer.parseInt(req.getParameter("rIds"));
        int typeId = Integer.parseInt(req.getParameter("type.id"));
        Rule rule = jdbcStore.findRuleById(ruleId);
        AccidentType accidentType = jdbcStore.findAccidentTypeById(typeId);
        accident.setRule(rule);
        accident.setAccidentType(accidentType);
        jdbcStore.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {

        List<AccidentType> types = jdbcStore.getAllAccidentTypes();
        model.addAttribute("types", types);

        List<Rule> rules = jdbcStore.getAllRules();
        model.addAttribute("rules", rules);

        model.addAttribute("accident", jdbcStore.findAccidentById(id));

        return "accident/update";
    }

}
