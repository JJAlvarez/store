package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

    Rol findByName(String name);
}
