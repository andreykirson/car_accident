package repository;

import model.Accident;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

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

    public void addOrUpdateAccident(Integer id, Accident accident) {
        if (this.accidents.containsKey(id)) {
            this.accidents.replace(id, accident);
        }
        this.accidents.put(id, accident);
    }

    public Accident getAccident(Integer id) {
        return this.accidents.get(id);
    }

    public Set<Integer> getKeys() {
        return accidents.keySet();
    }

    public Collection<Accident> getAll() {
        return this.accidents.values();
    }



}
