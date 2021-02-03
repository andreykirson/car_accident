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
import repository.AccidentRepository;
import repository.AccidentTypeRepository;
import repository.RulesRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RulesRepository rulesRepository;

    public AccidentControl(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RulesRepository rulesRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.rulesRepository = rulesRepository;
    }


    @GetMapping("/create")
    public String create(Model model) {
        Collection<AccidentType> types = accidentTypeRepository.findAll();
        model.addAttribute("types", types);
        Collection<Rule> rules = rulesRepository.findAll();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        String typeId = req.getParameter("type.id");
        int[] numbers = Arrays.stream(ruleIds).mapToInt(Integer::parseInt).toArray();
        List<Integer> listId = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        List<Rule> rules = rulesRepository.findByRuleIdIn(listId);
        AccidentType accidentType = accidentTypeRepository.findAccidentTypeByTypeId(Integer.valueOf(typeId));
        accident.setAccidentType(accidentType);
        accident.setRules(rules);
        accidentRepository.save(accident);
        return "redirect:/";
    }


    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model) {
        Collection<AccidentType> types = accidentTypeRepository.findAll();
        model.addAttribute("types", types);
        Collection<Rule> rules = rulesRepository.findAll();
        model.addAttribute("rules", rules);
        Accident accident = accidentRepository.findAccidentByAccidentId(Integer.valueOf(id));
        model.addAttribute("accident", accident);
        return "accident/update";
    }

}
