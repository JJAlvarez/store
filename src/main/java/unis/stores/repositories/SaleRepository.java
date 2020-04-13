package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer> {
}
