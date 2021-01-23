package service;

import model.Accident;
import org.springframework.stereotype.Service;
import repository.AccidentMem;
import java.util.Collection;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Service
public class AccidentService {

    AccidentMem accidentMem = new AccidentMem();

    public void addAccident(Accident accident) {
        this.accidentMem.addAccident(accident.getId(), accident);
    }

    public Accident getAccident(Integer id) {
        return accidentMem.getAccident(id);
    }

    public Collection<Accident> getAll() {
        return accidentMem.getAll();
    }

}
