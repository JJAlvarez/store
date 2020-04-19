package unis.stores.services.fabric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Brand;
import unis.stores.entities.Fabric;
import unis.stores.repositories.FabricRepository;

import java.util.Date;
import java.util.List;

@Service
public class FabricService implements IFabricService {

    @Autowired
    private FabricRepository fabricRepository;

    @Override
    public List<Fabric> getFabrics() {
        return (List<Fabric>) fabricRepository.findAll();
    }

    @Override
    public Fabric getFabric(int id) {
        return fabricRepository.findOne(id);
    }

    @Override
    public Fabric createFabric(String name, String ip, String servicePassword) {
        Fabric fabric = new Fabric();
        fabric.setName(name);
        fabric.setIp(ip);
        fabric.setServicePassword(servicePassword);
        fabric.setLastDateRequest(new Date());

        return fabricRepository.save(fabric);
    }

    @Override
    public Fabric updateFabric(int id, String name, String ip, String servicePassword) {
        if (!fabricRepository.exists(id))
            return null;

        Fabric fabric = fabricRepository.findOne(id);
        fabric.setName(name);
        fabric.setServicePassword(servicePassword);
        fabric.setIp(ip);

        return fabricRepository.save(fabric);
    }

    @Override
    public boolean deleteFabric(int id) {
        if (!fabricRepository.exists(id))
            return false;

        fabricRepository.delete(id);
        return true;
    }
}
