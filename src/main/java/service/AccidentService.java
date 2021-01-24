package service;

import model.Accident;
import org.springframework.stereotype.Service;
import repository.AccidentMem;
import java.util.Collection;
import java.util.Collections;

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

    public void saveAccident(Accident accident) {
        Integer newId = Collections.max(accidentMem.getKeys()) + 1;
        accident.setId(newId);
        this.accidentMem.addAccident(accident.getId(), accident);
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

}
