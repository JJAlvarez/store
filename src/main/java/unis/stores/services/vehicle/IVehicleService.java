package unis.stores.services.vehicle;

import unis.stores.entities.Vehicle;

import java.util.List;

public interface IVehicleService {

    List<Vehicle> getVehicles();
    Vehicle getVehicle(String universalCode);
    Vehicle createVehicle(String universalCode, String brand, String line, String year);
    Vehicle updateVehicle(String universalCode, String brand, String line, String year);
    boolean deleteVehicle(String universalCode);
}
