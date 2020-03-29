package unis.stores.services.orderstate;

import unis.stores.entities.OrderState;

import java.util.List;

public interface IOrderStateService {

    List<OrderState> getOrderStates();
    OrderState getOrderState(int id);
    OrderState createOrderState(String name);
    OrderState updateOrderState(int id, String name);
    boolean deleteOrderState(int id);
    OrderState searchByName(String name);
}
