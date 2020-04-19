package unis.stores.services.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.entities.Line;
import unis.stores.entities.Vehicle;
import unis.stores.repositories.BrandRepository;
import unis.stores.repositories.LineRepository;
import unis.stores.repositories.VehicleRepository;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private LineRepository lineRepository;

    @Override
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicle(String universalCode) {
        if (!vehicleRepository.exists(universalCode))
            return null;

        return vehicleRepository.findOne(universalCode);
    }

    @Override
    public Vehicle createVehicle(String universalCode, int brandId, int lineId, String year) {
        if (vehicleRepository.exists(universalCode) || !brandRepository.exists(brandId) || !lineRepository.exists(lineId))
            return null;

        Vehicle newVehicle = new Vehicle();
        newVehicle.setUniversalCode(universalCode);

        Brand brand = brandRepository.findOne(brandId);
        Line line = lineRepository.findOne(lineId);

        newVehicle.setBrand(brand);
        newVehicle.setLine(line);
        newVehicle.setYear(year);

        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Vehicle updateVehicle(String universalCode, int brandId, int lineId, String year) {
        if (!vehicleRepository.exists(universalCode) || !brandRepository.exists(brandId) || !lineRepository.exists(lineId))
            return null;

        Vehicle updateVehicle = vehicleRepository.findOne(universalCode);

        Brand brand = brandRepository.findOne(brandId);
        Line line = lineRepository.findOne(lineId);

        updateVehicle.setBrand(brand);
        updateVehicle.setLine(line);
        updateVehicle.setYear(year);

        return vehicleRepository.save(updateVehicle);
    }

    @Override
    public boolean deleteVehicle(String universalCode) {
        if (!vehicleRepository.exists(universalCode))
            return false;

        vehicleRepository.delete(universalCode);
        return true;
    }
}
