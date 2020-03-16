package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByPartNo(String partNo);
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
    List<Product> findByPrice(double price);

}
