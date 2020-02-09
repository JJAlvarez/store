package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


}
