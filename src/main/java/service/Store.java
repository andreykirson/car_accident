package service;

import model.Accident;
import model.AccidentType;
import model.Rule;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @since 26/01/21
 */

public interface Store {
   void addAccident(Accident accident, List<Rule> rules, AccidentType accidentType);
   void updateAccident(Accident accident, List<Rule> rules, AccidentType accidentType);
   List<Accident> getAllAccidents();
   Accident findAccidentById(String id);
   List<Rule> getRulesByAccidentId(int id);
   AccidentType getAccidentTypeByAccidentId(int id);
   List<Rule> getAllRules();
   List<AccidentType> getAllAccidentType();
   List<Rule> getAllRulesByIds(String[] ids);
   AccidentType getAccidentTypeById(String id);
}
