package unis.stores.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.User;
import unis.stores.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void createUser(String firstName, String lastName, String username, String password) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);

        userRepository.save(newUser);
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, String username) {
        if (!userRepository.exists(id)) {
            return;
        }

        User updateUser = userRepository.findOne(id);

        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setUsername(username);

        userRepository.save(updateUser);
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
    public boolean login(String username, String password) {
        User login = userRepository.findByUsernameAndPassword(username, password);

        return login != null;
    }
}
