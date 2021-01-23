package control;

import model.Accident;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AccidentMem;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {

        Accident accidentOne = new Accident();
        accidentOne.setId(1);
        accidentOne.setAddress("Baker Street 221 b");
        accidentOne.setName("Car accident");
        accidentOne.setText("too hard");

        Accident accidentTwo = new Accident();
        accidentTwo.setId(2);
        accidentTwo.setAddress("Beverly Hills 111");
        accidentTwo.setName("Motor cycle accident");
        accidentTwo.setText("light");

        AccidentMem accidentMem = new AccidentMem();
        accidentMem.addAccident(accidentOne.getId(), accidentOne);
        accidentMem.addAccident(accidentTwo.getId(), accidentTwo);

        model.addAttribute("accidents", accidentMem);
        return "index";
    }
}