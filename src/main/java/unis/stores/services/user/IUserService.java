package unis.stores.services.user;

import unis.stores.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(int id);
    User createUser(String firstName, String lastName, String username, String password, int rolId);
    User updateUser(int id, String firstName, String lastName, String username);
    User updateUserRol(int id, int idRol);
    boolean updatePassword(int id, String newPassword);
    boolean deleteUser(int id);

    User login(String username, String password);
    boolean checkExistUser(String username);
}
