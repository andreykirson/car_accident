package repository;

import model.Accident;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();


    public AccidentMem() {
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
        accidents.put(accidentOne.getId(), accidentOne);
        accidents.put(accidentTwo.getId(), accidentTwo);
    }

    public void addAccident(Integer id, Accident accident) {
        this.accidents.put(id, accident);
    }

    public Accident getAccident(Integer id) {
        return this.accidents.get(id);
    }

    public Collection<Accident> getAll() {
        return this.accidents.values();
    }

}
