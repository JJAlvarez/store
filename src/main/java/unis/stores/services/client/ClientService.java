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
    public Client createClient(String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!subscriptionRepository.exists(subscriptionId))
            return null;

        Client newClient = new Client();

        newClient.setName(name);
        newClient.setNit(nit);
        newClient.setEmail(email);
        newClient.setPhone(phone);
        newClient.setImage(image);
        newClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        return clientRepository.save(newClient);
    }

    @Override
    public Client updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId) {
        if (!clientRepository.exists(id) || !subscriptionRepository.exists(subscriptionId))
            return null;

        Client updateClient = clientRepository.findOne(id);

        updateClient.setName(name);
        updateClient.setNit(nit);
        updateClient.setEmail(email);
        updateClient.setPhone(phone);
        updateClient.setImage(image);
        updateClient.setSubscription(subscriptionRepository.findOne(subscriptionId));

        return clientRepository.save(updateClient);
    }

    @Override
    public boolean deleteClient(int id) {
        if (!clientRepository.exists(id))
            return false;

        clientRepository.delete(id);
        return true;
    }

    @Override
    public Client renewMembership(int id) {
        return null;
    }

    @Override
    public Client searchByNit(String nit) {
        return clientRepository.findByNit(nit);
    }
}
