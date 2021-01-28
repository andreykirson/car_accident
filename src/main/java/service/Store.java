package service;

import model.Accident;
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
   Accident findAccidentById(int id);
}
