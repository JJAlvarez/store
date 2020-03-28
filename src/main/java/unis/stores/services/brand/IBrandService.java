package unis.stores.services.brand;

import unis.stores.entities.Brand;

import java.util.List;

public interface IBrandService {

    List<Brand> getBrands();
    Brand getBrand(int id);
    Brand createBrand(String name);
    Brand updateBrand(int id, String name);
    boolean deleteBrand(int id);
    Brand searchByName(String name);
}
