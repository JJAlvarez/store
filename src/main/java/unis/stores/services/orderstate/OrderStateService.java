package unis.stores.services.orderstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.OrderState;
import unis.stores.repositories.OrderStateRepository;

import java.util.List;

@Service
public class OrderStateService implements IOrderStateService{

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public List<OrderState> getOrderStates() {
        return (List<OrderState>) orderStateRepository.findAll();
    }

    @Override
    public OrderState getOrderState(int id) {
        return orderStateRepository.findOne(id);
    }

    @Override
    public OrderState createOrderState(String name) {
        OrderState orderState = new OrderState();
        orderState.setName(name);

        return orderStateRepository.save(orderState);
    }

    @Override
    public OrderState updateOrderState(int id, String name) {
        if (!orderStateRepository.exists(id))
            return null;

        OrderState orderState = orderStateRepository.findOne(id);
        orderState.setName(name);

        return orderStateRepository.save(orderState);
    }

    @Override
    public boolean deleteOrderState(int id) {
        if (!orderStateRepository.exists(id))
            return false;

        orderStateRepository.delete(id);
        return true;
    }

    @Override
    public OrderState searchByName(String name) {
        return orderStateRepository.findByName(name);
    }
}
