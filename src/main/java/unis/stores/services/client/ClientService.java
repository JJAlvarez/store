package unis.stores.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Client;
import unis.stores.repositories.ClientRepository;
import unis.stores.repositories.SubscriptionRepository;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Client> getClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client getClientById(int id) {
        if (!clientRepository.exists(id))
            return null;

        return clientRepository.findOne(id);
    }

    @Override
    public void createClient(String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!subscriptionRepository.exists(subscriptionId))
            return;

        Client newClient = new Client();

        newClient.setName(name);
        newClient.setNit(nit);
        newClient.setEmail(email);
        newClient.setPhone(phone);
        newClient.setImage(image);
        newClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        clientRepository.save(newClient);
    }

    @Override
    public void updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!clientRepository.exists(id) || !subscriptionRepository.exists(subscriptionId))
            return;

        Client updateClient = clientRepository.findOne(id);

        updateClient.setName(name);
        updateClient.setNit(nit);
        updateClient.setEmail(email);
        updateClient.setPhone(phone);
        updateClient.setImage(image);
        updateClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        clientRepository.save(updateClient);
    }

    @Override
    public void deleteClient(int id) {
        if (!clientRepository.exists(id))
            return;

        clientRepository.delete(id);
    }
}
