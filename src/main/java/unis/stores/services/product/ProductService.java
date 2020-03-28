package unis.stores.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Product;
import unis.stores.entities.Vehicle;
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
    public Product assignVehicle(int id, String vehicleId) {
        if (!productRepository.exists(id) || !vehicleRepository.exists(vehicleId))
            return null;

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        Product product = productRepository.findOne(id);

        product.getVehicles().add(vehicle);

        return productRepository.save(product);
    }

    @Override
    public Product unAssignVehicle(int id, String vehicleId) {
        if (!productRepository.exists(id) || !vehicleRepository.exists(vehicleId))
            return null;

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        Product product = productRepository.findOne(id);

        product.getVehicles().remove(vehicle);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, String name, String description, String partNo, double price) {
        if (!productRepository.exists(id))
            return null;

        Product updatedProduct = productRepository.findOne(id);

        updatedProduct.setName(name);
        updatedProduct.setDescription(description);
        updatedProduct.setPartNo(partNo);
        updatedProduct.setPrice(price);

        return productRepository.save(updatedProduct);
    }

    @Override
    public Product updateStock(int id, int stock) {
        if (!productRepository.exists(id))
            return null;

        Product product = productRepository.findOne(id);
        product.setStock(stock);

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        if (!productRepository.exists(id))
            return false;

        productRepository.delete(id);
        return true;
    }
}
