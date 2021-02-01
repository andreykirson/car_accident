//package control;
//
//import model.Accident;
//import model.AccidentType;
//import model.Rule;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import repository.AccidentRepository;
//import repository.HbmStore;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.List;
//
//@Controller
//public class AccidentControl {
//
//    private final AccidentRepository store;
//
//    public AccidentControl(AccidentRepository store) {
//        this.store = store;
//    }
//
//
//    @GetMapping("/create")
//    public String create(Model model) {
//        Collection<AccidentType> types = store.getAllAccidentType();
//        model.addAttribute("types", types);
//        Collection<Rule> rules = store.getAllRules();
//        model.addAttribute("rules", rules);
//        return "accident/create";
//    }
//
//    @PostMapping("/save")
//    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
//        String[] ruleIds = req.getParameterValues("rIds");
//        String typeId = req.getParameter("type.id");
//        List<Rule> rules = store.getAllRulesByIds(ruleIds);
//        AccidentType accidentType = store.getAccidentTypeById(typeId);
//        store.addAccident(accident, rules, accidentType);
//        return "redirect:/";
//    }
//
//    @GetMapping("/update")
//    public String update(@RequestParam("id") String id, Model model) {
//        Collection<AccidentType> types = store.getAllAccidentType();
//        model.addAttribute("types", types);
//        Collection<Rule> rules = store.getAllRules();
//        model.addAttribute("rules", rules);
//        Accident accident = store.findAccidentById(id);
//        model.addAttribute("accident", accident);
//        return "accident/update";
//    }
//
//}
