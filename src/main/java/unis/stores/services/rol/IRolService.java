package unis.stores.services.rol;

import unis.stores.entities.Rol;

import java.util.List;

public interface IRolService {
    List<Rol> getRoles();
    Rol getRolById(int id);
    Rol createRol(String name);
    Rol updateRol(int id, String name);
    boolean deleteRol(int id);
    Rol searchByName(String name);
}
