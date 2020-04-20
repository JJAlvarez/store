package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.ProductRequest;

import java.util.List;

@Repository
public interface ProductRequestRepository extends CrudRepository<ProductRequest, Integer> {
    List<ProductRequest> findAllByRequest_Id(int requestId);
}
