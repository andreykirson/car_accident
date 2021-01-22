package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> str = new ArrayList<>();
        str.add("Oleg");
        str.add("Jhon");
        str.add("Albert");
        str.add("Leonard");
        model.addAttribute(str);
        return "index";
    }
}