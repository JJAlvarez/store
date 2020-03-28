package unis.stores.services.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.repositories.BrandRepository;

import java.util.List;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public Brand getBrand(int id) {
        return brandRepository.findOne(id);
    }

    @Override
    public Brand createBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);

        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(int id, String name) {
        if (!brandRepository.exists(id))
            return null;

        Brand brand = brandRepository.findOne(id);
        brand.setName(name);

        return brandRepository.save(brand);
    }

    @Override
    public boolean deleteBrand(int id) {
        if (!brandRepository.exists(id))
            return false;

        brandRepository.delete(id);
        return true;
    }

    @Override
    public Brand searchByName(String name) {
        return brandRepository.findByName(name);
    }
}
