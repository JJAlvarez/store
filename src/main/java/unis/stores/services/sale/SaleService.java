package unis.stores.services.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.OrderState;
import unis.stores.entities.Sale;
import unis.stores.repositories.OrderStateRepository;
import unis.stores.repositories.SaleRepository;

import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private OrderStateRepository orderStateRepository;

    @Override
    public List<Sale> getSales() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale getSale(int id) {
        return saleRepository.findOne(id);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSaleState(int id, int saleState) {
        if (!orderStateRepository.exists(saleState) || !saleRepository.exists(id))
            return null;

        Sale sale = saleRepository.findOne(id);
        OrderState orderState = orderStateRepository.findOne(saleState);

        sale.setOrderState(orderState);

        return saleRepository.save(sale);
    }
}
