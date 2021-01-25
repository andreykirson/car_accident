package service;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.stereotype.Service;
import repository.AccidentMem;
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

    public Accident getAccident(Integer id) {
        return accidentMem.getAccident(id);
    }

    public void saveAccident(Accident accident, AccidentType accidentType, Rule rule) {
        Integer rs = accident.getId();
        if (rs == null) {
            Integer newId = Collections.max(accidentMem.getKeys()) + 1;
            accident.setId(newId);
        }

        this.accidentMem.addOrUpdateAccident(accident.getId(), accident, accidentType, rule);
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

    public AccidentType getAccidentTypeById(int id) {
        return accidentMem.getAccidentTypeById(id);
    }

    public Rule getRuleById(int id) {
        return accidentMem.getRuleById(id);
    }

}
