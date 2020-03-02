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
    public Rol createRol(String name) {
        Rol newRol = new Rol();
        newRol.setName(name);

        return rolRepository.save(newRol);
    }

    @Override
    public Rol updateRol(int id, String name) {
        if (!rolRepository.exists(id))
            return null;

        Rol updateRol = rolRepository.findOne(id);
        updateRol.setName(name);

        return  rolRepository.save(updateRol);
    }

    @Override
    public boolean deleteRol(int id) {
        if (!rolRepository.exists(id)) {
            return false;
        }

        rolRepository.delete(id);
        return true;
    }

    @Override
    public Rol searchByName(String name) {
        return rolRepository.findByName(name);
    }
}
