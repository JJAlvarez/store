package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {
}
