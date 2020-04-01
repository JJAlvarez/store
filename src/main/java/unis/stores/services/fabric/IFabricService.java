package unis.stores.services.fabric;

import unis.stores.entities.Fabric;

import java.util.List;

public interface IFabricService {

    List<Fabric> getFabrics();
    Fabric getFabric(int id);
    Fabric createFabric(String name, String ip, String servicePassword);
    Fabric updateFabric(int id, String name, String ip, String servicePassword);
    boolean deleteFabric(int id);
}
