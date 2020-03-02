package unis.stores.services.subscription;

import unis.stores.entities.Subscription;

import java.util.List;

public interface ISubscriptionService {
    List<Subscription> getSubscriptions();
    Subscription getSubscriptionById(int id);
    void createSubscription(String name);
    void updateSubscription(int id, String name);
    void deleteSubscription(int id);
}
