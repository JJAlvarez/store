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
    public Subscription createSubscription(String name) {
        Subscription newSubscription = new Subscription();

        newSubscription.setName(name);

        return subscriptionRepository.save(newSubscription);
    }

    @Override
    public Subscription updateSubscription(int id, String name) {
        if (!subscriptionRepository.exists(id))
            return null;

        Subscription updateSubscription = subscriptionRepository.findOne(id);

        updateSubscription.setName(name);

        return subscriptionRepository.save(updateSubscription);
    }

    @Override
    public boolean deleteSubscription(int id) {
        if (!subscriptionRepository.exists(id))
            return false;

        subscriptionRepository.delete(id);
        return true;
    }
}
