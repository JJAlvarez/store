package unis.stores.services.user;

import unis.stores.entities.User;

import java.util.List;

public interface IUserService {
    public List<User> getUsers();
    public User getUserById(int id);
    public void createUser(String firstName, String lastName, String username, String password);
    public void updateUser(int id, String firstName, String lastName, String username);
    public void updatePassword(int id, String newPassword);
    public void deleteUser(int id);
}
