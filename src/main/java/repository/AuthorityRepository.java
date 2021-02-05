package repository;

import org.springframework.data.repository.CrudRepository;
import model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}