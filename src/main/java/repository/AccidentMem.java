package repository;

import model.Accident;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents;

    public void addAccident(Integer id, Accident accident) {
        this.accidents.put(id, accident);
    }

    public Accident getAccident(Integer id) {
        return this.accidents.get(id);
    }

}
