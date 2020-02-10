package unis.stores.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import unis.stores.entities.User;
import unis.stores.result.user.LoginResult;
import unis.stores.services.user.UserService;
import unis.stores.constants.*;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestParam Map<String, String> body) {
        if (body.get(Constants.USERNAME_LABEL) == null || body.get(Constants.PASSWORD_LABEL) == null)
            return ResponseEntity.badRequest().build();

        User loggedUser = userService.login(body.get(Constants.USERNAME_LABEL), body.get(Constants.PASSWORD_LABEL));

        if (loggedUser != null) {
            return ResponseEntity.ok().body(loggedUser);
        } else {
            return ResponseEntity.badRequest().body(new LoginResult(false, "Bad Credentials!"));
        }
    }

}
