package unis.stores.services.user;

import unis.stores.entities.User;

import java.util.List;

public interface IUserService {
    public List<User> getUsers();
    public User getUserById(int id);
    public User createUser(String firstName, String lastName, String username, String password, int rolId);
    public User updateUser(int id, String firstName, String lastName, String username);
    public User updateUserRol(int id, int idRol);
    public void updatePassword(int id, String newPassword);
    public void deleteUser(int id);

    public User login(String username, String password);
    public boolean checkExistUser(String username);
}
