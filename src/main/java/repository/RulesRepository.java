package repository;

import model.Rule;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;
import java.util.List;

public interface RulesRepository extends CrudRepository<Rule, Integer> {
    Collection<Rule> findAll();
    List<Rule> findByRuleId(List<Integer> ids);
    List<Rule> findByRuleIdIn(List<Integer> ids);

}
