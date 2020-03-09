package unis.stores.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Product;
import unis.stores.repositories.ProductRepository;
import unis.stores.repositories.VehicleRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> searchProduct(String name, String description, String partNo, double price) {
        return null;
    }

    @Override
    public Product createProduct(String name, String description, String partNo, double price, int stock) {
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPartNo(partNo);
        product.setPrice(price);
        product.setStock(stock);

        return productRepository.save(product);
    }

    @Override
    public Product assignVehicle(int id, int vehicleId) {
        return null;
    }

    @Override
    public Product unAssignVehicle(int id, int vehicleId) {
        return null;
    }

    @Override
    public Product updateProduct(int id, String name, String description, String partNo, double price) {
        return null;
    }

    @Override
    public Product updateStock(int id, int stock) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }
}
