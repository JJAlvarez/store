package unis.stores.services.client;

import unis.stores.entities.Client;

import java.util.List;

public interface IClientService {

    List<Client> getClients();
    Client getClientById(int id);
    Client createClient(String name, String nit, String email, String phone, String image, int subscriptionId);
    Client updateClient(int id, String name, String nit, String email, String phone, String image, int subscriptionId);
    boolean deleteClient(int id);

    Client searchByNit(String nit);
}
