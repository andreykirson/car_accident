package repository;

import model.Accident;
import org.springframework.data.repository.CrudRepository;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
//    List<Accident> findAll();
}
