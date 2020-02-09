package unis.stores.services.rol;

import unis.stores.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> getRoles();
    public Rol getRolById(int id);
    public void createRol(String name);
    public void updateRol(int id, String name);
    public void deleteRol(int id);
}
