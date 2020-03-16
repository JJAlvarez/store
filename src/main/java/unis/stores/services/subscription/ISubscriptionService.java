package unis.stores.services.subscription;

import unis.stores.entities.Subscription;

import java.util.List;

public interface ISubscriptionService {
    List<Subscription> getSubscriptions();
    Subscription getSubscriptionById(int id);
    Subscription createSubscription(String name);
    Subscription updateSubscription(int id, String name);
    boolean deleteSubscription(int id);
}
