package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.RequestState;

@Repository
public interface RequestStateRepository extends CrudRepository<RequestState, Integer> {

    RequestState findByName(String name);
}
