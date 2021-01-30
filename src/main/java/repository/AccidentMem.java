package repository;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @version 1
 * @since 23/01/21
 */


@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final Map<Integer, AccidentType> types = new HashMap<>();

    private final Map<Integer, Rule> rules = new HashMap<>();


    public AccidentMem() {
        Accident accidentOne = new Accident();
        accidentOne.setAccidentId(1);
        accidentOne.setAccidentAddress("Baker Street 221 b");
        accidentOne.setAccidentName("Car accident");
        accidentOne.setAccidentText("too hard");
        Accident accidentTwo = new Accident();
        accidentTwo.setAccidentId(2);
        accidentTwo.setAccidentAddress("Beverly Hills 111");
        accidentTwo.setAccidentName("Motor cycle accident");
        accidentTwo.setAccidentText("light");
        accidents.put(accidentOne.getAccidentId(), accidentOne);
        accidents.put(accidentTwo.getAccidentId(), accidentTwo);

        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public void addOrUpdateAccident(Integer id, Accident accident, AccidentType accidentType, List<Rule> rules) {
        accident.setRules(rules);
        accident.setAccidentType(accidentType);
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

    public AccidentType getAccidentTypeById(int id) {
        return types.get(id);
    }

    public Collection<AccidentType> getAllAccidentType() {
        return this.types.values();
    }

    public Collection<Rule> getAllRule() {
        return this.rules.values();
    }

    public List<Rule> getRulesByIds(int[] id) {
        List<Rule> newRules = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            newRules.add(rules.get(id[i]));
        }
        return newRules;
    }
}
