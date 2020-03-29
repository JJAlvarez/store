package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Line;

@Repository
public interface LineRepository extends CrudRepository<Line, Integer> {

    Line findByName(String name);
}
