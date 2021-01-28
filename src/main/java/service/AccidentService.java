package service;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.stereotype.Service;
import repository.AccidentMem;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Accident getAccident(String id) {
        return accidentMem.getAccident(Integer.parseInt(id));
    }

    public void saveAccident(Accident accident, List<Rule> rules, AccidentType type) {
        Integer rs = accident.getAccidentId();
        if (rs == 0) {
            Integer newId = Collections.max(accidentMem.getKeys()) + 1;
            accident.setAccidentId(newId);
        }
        this.accidentMem.addOrUpdateAccident(accident.getAccidentId(), accident, type, rules);
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return accidentMem.getAllAccidentType();
    }

    public Collection<Rule> getAllRule() {
        return accidentMem.getAllRule();
    }

    public AccidentType getAccidentTypeById(String id) {
        return accidentMem.getAccidentTypeById(Integer.parseInt(id));
    }

    public List<Rule> getRulesByIds(String[] ids) {
        int[] numbers = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        return accidentMem.getRulesByIds(numbers);
    }

}
