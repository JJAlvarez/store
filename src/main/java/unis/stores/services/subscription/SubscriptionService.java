package unis.stores.services.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Subscription;
import unis.stores.repositories.SubscriptionRepository;

import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getSubscriptions() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscriptionById(int id) {
        if (!subscriptionRepository.exists(id))
            return null;

        return subscriptionRepository.findOne(id);
    }

    @Override
    public void createSubscription(String name) {
        Subscription newSubscription = new Subscription();

        newSubscription.setName(name);

        subscriptionRepository.save(newSubscription);
    }

    @Override
    public void updateSubscription(int id, String name) {
        if (!subscriptionRepository.exists(id))
            return;

        Subscription updateSubscription = subscriptionRepository.findOne(id);

        updateSubscription.setName(name);

        subscriptionRepository.save(updateSubscription);
    }

    @Override
    public void deleteSubscription(int id) {
        if (!subscriptionRepository.exists(id))
            return;

        subscriptionRepository.delete(id);
    }
}
