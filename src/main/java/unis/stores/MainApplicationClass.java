package unis.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import unis.stores.services.user.UserService;

@SpringBootApplication
public class MainApplicationClass {

    @Autowired
    UserService userService;

    public static void main(String args[]) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}



