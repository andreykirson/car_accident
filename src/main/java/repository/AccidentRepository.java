package repository;

import model.Accident;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findAll();
    Accident findAccidentByAccidentId(int id);
    Accident save(Accident accident);
}
