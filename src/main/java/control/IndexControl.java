package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.JDBCStore;

/**
 * @author Andrey
 * @version 1
 * @since 23/01/21
 */

@Controller
public class IndexControl {

    private final JDBCStore jdbcStore;

    public IndexControl(JDBCStore jdbcStore) {
        this.jdbcStore = jdbcStore;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", jdbcStore.getAllAccidents());
        return "index";
    }
}