package unis.stores.services.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Rol;
import unis.stores.repositories.RolRepository;

import java.util.List;

@Service
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> getRoles() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Rol getRolById(int id) {
        if (!rolRepository.exists(id))
            return null;

        return rolRepository.findOne(id);
    }

    @Override
    public void createRol(String name) {
        Rol newRol = new Rol();
        newRol.setName(name);

        rolRepository.save(newRol);
    }

    @Override
    public void updateRol(int id, String name) {
        if (!rolRepository.exists(id))
            return;

        Rol updateRol = rolRepository.findOne(id);
        updateRol.setName(name);

        rolRepository.save(updateRol);
    }

    @Override
    public void deleteRol(int id) {
        if (!rolRepository.exists(id)) {
            return;
        }

        rolRepository.delete(id);
    }
}
