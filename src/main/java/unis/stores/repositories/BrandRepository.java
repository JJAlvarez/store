package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Brand findByName(String name);
}
