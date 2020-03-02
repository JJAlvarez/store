package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Client findByNit(String nit);
}
