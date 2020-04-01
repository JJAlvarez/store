package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Fabric;

@Repository
public interface FabricRepository extends CrudRepository<Fabric, Integer> {
}
