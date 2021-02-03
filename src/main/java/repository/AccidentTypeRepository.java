package repository;

import model.AccidentType;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
    Collection<AccidentType> findAll();
    AccidentType findAccidentTypeByTypeId(int id);
}
