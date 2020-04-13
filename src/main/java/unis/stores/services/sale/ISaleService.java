package unis.stores.services.sale;

import unis.stores.entities.Sale;

import java.util.List;

public interface ISaleService {

    List<Sale> getSales();
    Sale getSale(int id);
    Sale createSale(Sale sale);
    Sale updateSaleState(int id, int saleState);
}
