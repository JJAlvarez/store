package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unis.stores.constants.Constants;
import unis.stores.entities.Client;
import unis.stores.result.client.CreateClientResult;
import unis.stores.services.client.ClientService;

import java.util.Map;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Object> create(@RequestParam Map<String, String> body) {
        if (!body.containsKey(Constants.CLIENT_NAME_LABEL) || !body.containsKey(Constants.CLIENT_EMAIL_LABEL) ||
            !body.containsKey(Constants.CLIENT_NIT_LABEL) || !body.containsKey(Constants.CLIENT_PHONE_LABEL) ||
            !body.containsKey(Constants.CLIENT_IMAGE_LABEL) || !body.containsKey(Constants.CLIENT_SUBSCRIPTION_LABEL))
            return ResponseEntity.badRequest().body(new CreateClientResult(false, "Bad Request"));

        if (clientService.searchByNit(body.get(Constants.CLIENT_NIT_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateClientResult(false, "The client already exists"));

        try {
            int subscriptionId = Integer.parseInt(body.get(Constants.CLIENT_SUBSCRIPTION_LABEL));

            Client createdClient = clientService.createClient(body.get(Constants.CLIENT_NAME_LABEL), body.get(Constants.CLIENT_NIT_LABEL),
                    body.get(Constants.CLIENT_EMAIL_LABEL), body.get(Constants.CLIENT_PHONE_LABEL), body.get(Constants.CLIENT_IMAGE_LABEL), subscriptionId);

            if (createdClient == null)
                return ResponseEntity.badRequest().body(new CreateClientResult(false, "Error creating the new client"));
            else
                return ResponseEntity.ok().body(createdClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateClientResult(false, "Bad Request"));
        }
    }
}
