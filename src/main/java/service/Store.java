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
   void addAccident(Accident accident);
   void updateAccident(Accident accident);
   List<Accident> getAllAccidents();
   List<AccidentType> getAllAccidentTypes();
   List<Rule> getAllRules();
   Rule findRuleById(int id);
   AccidentType findAccidentTypeById(int id);
   Accident findAccidentById(int id);
}
