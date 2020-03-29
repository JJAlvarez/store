package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.OrderState;

@Repository
public interface OrderStateRepository extends CrudRepository<OrderState, Integer> {

    OrderState findByName(String name);
}
