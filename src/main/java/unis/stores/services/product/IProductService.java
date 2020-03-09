package unis.stores.services.product;

import unis.stores.entities.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProducts();
    Product getProduct(int id);
    List<Product> searchProduct(String name, String description, String partNo, double price);
    Product createProduct(String name, String description, String partNo, double price, int stock);
    Product assignVehicle(int id, int vehicleId);
    Product unAssignVehicle(int id, int vehicleId);
    Product updateProduct(int id, String name, String description, String partNo, double price);
    Product updateStock(int id, int stock);
    boolean deleteProduct(int id);

}
