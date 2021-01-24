package repository;

import model.Accident;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Andrey
 * @version 1
 * @since 24/01/21
 */

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

}
