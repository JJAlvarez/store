package unis.stores.services.client;

import unis.stores.entities.Client;

import java.util.List;

public interface IClientService {

    List<Client> getClients();
    Client getClientById(int id);
    void createClient(String name, String nit, String email, String phone, String image, int subscriptionId);
    void updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId);
    void deleteClient(int id);

}
