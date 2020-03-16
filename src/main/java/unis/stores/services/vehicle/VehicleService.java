package unis.stores.services.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Vehicle;
import unis.stores.repositories.VehicleRepository;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

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
    public Vehicle createVehicle(String universalCode, String brand, String line, String year) {
        if (vehicleRepository.exists(universalCode))
            return null;

        Vehicle newVehicle = new Vehicle();
        newVehicle.setUniversalCode(universalCode);
        newVehicle.setBrand(brand);
        newVehicle.setLine(line);
        newVehicle.setYear(year);

        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Vehicle updateVehicle(String universalCode, String brand, String line, String year) {
        if (!vehicleRepository.exists(universalCode))
            return null;

        Vehicle updateVehicle = vehicleRepository.findOne(universalCode);
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
