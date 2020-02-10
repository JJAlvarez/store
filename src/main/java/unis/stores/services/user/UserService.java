package unis.stores.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Rol;
import unis.stores.entities.User;
import unis.stores.repositories.RolRepository;
import unis.stores.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User createUser(String firstName, String lastName, String username, String password, int rolId) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);

        Rol userRol = rolRepository.findOne(rolId);
        newUser.setRol(userRol);

        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(int id, String firstName, String lastName, String username) {
        if (!userRepository.exists(id)) {
            return null;
        }

        User updateUser = userRepository.findOne(id);

        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setUsername(username);

        return userRepository.save(updateUser);
    }

    @Override
    public User updateUserRol(int id, int idRol) {
        if (!userRepository.exists(id) || !rolRepository.exists(idRol))
            return null;

        User updateUser = userRepository.findOne(id);
        Rol updateRol = rolRepository.findOne(idRol);

        updateUser.setRol(updateRol);

        return userRepository.save(updateUser);
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        if (!userRepository.exists(id))
            return;

        User updateUser = userRepository.findOne(id);

        updateUser.setPassword(newPassword);

        userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.exists(id))
            return;

        userRepository.delete(id);
    }

    @Override
    public User login(String username, String password) {
        User login = userRepository.findByUsernameAndPassword(username, password);

        if (login != null)
            login.setPassword(null);

        return login;
    }

    @Override
    public boolean checkExistUser(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
