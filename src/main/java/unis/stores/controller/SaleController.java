package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.entities.Sale;
import unis.stores.result.sale.CreateSaleResult;
import unis.stores.result.sale.GetSaleResult;
import unis.stores.result.sale.UpdateSaleStateResult;
import unis.stores.services.sale.SaleService;

@CrossOrigin
@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/sale")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(saleService.getSales());
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetSaleResult(false, "Bad Request", null));

        try {
            int saleId = Integer.parseInt(id);

            Sale sale = saleService.getSale(saleId);

            if (sale == null)
                return ResponseEntity.badRequest().body(new GetSaleResult(false, "Sale doesn't exists", null));
            else
                return ResponseEntity.ok().body(sale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetSaleResult(false, e.getMessage(), null));
        }
    }

    @PostMapping("/sale")
    public ResponseEntity<Object> create(@RequestBody Sale sale) {
        if (sale.getDate() == null || sale.getTotal() == 0 || sale.getClient() == null || sale.getProductSales().size() == 0
        || sale.getOrderState() == null)
            return ResponseEntity.badRequest().body(new CreateSaleResult(false, "Bad Request", null));

        Sale createdSale = saleService.createSale(sale);

        if (createdSale == null)
            return  ResponseEntity.badRequest().body(new CreateSaleResult(false, "Error creating the sale", null));
        else
            return ResponseEntity.ok(new CreateSaleResult(true, "Sale created", createdSale));
    }

    @PutMapping("/sale")
    public ResponseEntity<Object> update(@RequestBody Sale sale) {
        if (sale.getOrderState() == null || sale.getId() == 0)
            return ResponseEntity.badRequest().body(new CreateSaleResult(false, "Bad Request", null));

        Sale updatedSale = saleService.updateSaleState(sale.getId(), sale.getOrderState().getId());

        if (updatedSale == null)
            return ResponseEntity.badRequest().body(new UpdateSaleStateResult(false, "Error updating the sale", null));
        else
            return ResponseEntity.ok(new UpdateSaleStateResult(false, "Sale updated", updatedSale));
    }
}
