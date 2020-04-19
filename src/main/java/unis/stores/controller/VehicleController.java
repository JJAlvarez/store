package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Vehicle;
import unis.stores.result.vehicle.CreateVehicleResult;
import unis.stores.result.vehicle.DeleteVehicleResult;
import unis.stores.result.vehicle.GetVehicleResult;
import unis.stores.result.vehicle.UpdateVehicleResult;
import unis.stores.services.vehicle.VehicleService;

import java.util.Map;

@CrossOrigin
@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.VEHICLE_ID_LABEL) || !body.containsKey(Constants.VEHICLE_BRAND_LABEL) ||
            !body.containsKey(Constants.VEHICLE_LINE_LABEL) || !body.containsKey(Constants.VEHICLE_YEAR_LABEL))
            return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Bad Request"));

        try {
            if (vehicleService.getVehicle(body.get(Constants.VEHICLE_ID_LABEL)) != null)
                return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Vehicle already exists!"));

            int brandId = Integer.parseInt(body.get(Constants.VEHICLE_BRAND_LABEL));
            int lineId = Integer.parseInt(body.get(Constants.VEHICLE_LINE_LABEL));

            Vehicle vehicle = vehicleService.createVehicle(body.get(Constants.VEHICLE_ID_LABEL), brandId,
                    lineId, body.get(Constants.VEHICLE_YEAR_LABEL));

            if (vehicle == null)
                return ResponseEntity.badRequest().body(new CreateVehicleResult(false, "Error creating the vehicle"));
            else
                return ResponseEntity.ok().body(vehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateVehicleResult(false, e.getMessage()));
        }
    }

    @PutMapping("/vehicle")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.VEHICLE_ID_LABEL) || !body.containsKey(Constants.VEHICLE_BRAND_LABEL) ||
                !body.containsKey(Constants.VEHICLE_LINE_LABEL) || !body.containsKey(Constants.VEHICLE_YEAR_LABEL))
            return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, "Bad Request"));

        try {
            int brandId = Integer.parseInt(body.get(Constants.VEHICLE_BRAND_LABEL));
            int lineId = Integer.parseInt(body.get(Constants.VEHICLE_LINE_LABEL));

            Vehicle updatedVehicle = vehicleService.updateVehicle(body.get(Constants.VEHICLE_ID_LABEL), brandId,
                    lineId, body.get(Constants.VEHICLE_YEAR_LABEL));

            if (updatedVehicle == null)
                return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, "Error updating the vehicle"));
            else
                return ResponseEntity.ok().body(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateVehicleResult(false, e.getMessage()));
        }
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String universalCode) {
        if (universalCode == null)
            return ResponseEntity.badRequest().body(new DeleteVehicleResult(false, "Bad Request"));

        if (vehicleService.deleteVehicle(universalCode))
            return ResponseEntity.ok().body(new DeleteVehicleResult(true, "Vehicle deleted successfully"));
        else
            return ResponseEntity.badRequest().body(new DeleteVehicleResult(false, "Error deleting the vehicle"));
    }

    @GetMapping("/vehicle")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(vehicleService.getVehicles());
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String universalCode) {
        if (universalCode == null)
            return ResponseEntity.badRequest().body(new GetVehicleResult(false, "Bad Request"));

        Vehicle vehicle = vehicleService.getVehicle(universalCode);

        if (vehicle == null)
            return ResponseEntity.badRequest().body(new GetVehicleResult(false, "Vehicle doesn't exists"));
        else
            return ResponseEntity.ok().body(vehicle);
    }
}
