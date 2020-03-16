package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Product;
import unis.stores.result.product.*;
import unis.stores.services.product.ProductService;
import unis.stores.services.vehicle.VehicleService;

import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/product")
    public ResponseEntity<CreateProductResult> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_NAME_LABEL) || !body.containsKey(Constants.PRODUCT_DESCRIPTION_LABEL)
        || !body.containsKey(Constants.PRODUCT_PART_NO_LABEL) || !body.containsKey(Constants.PRODUCT_PRICE_LABEL) ||
        !body.containsKey(Constants.PRODUCT_STOCK_LABEL))
            return ResponseEntity.badRequest().body(new CreateProductResult(false, "Bad Request", null));

        try {
            double price = Double.parseDouble(body.get(Constants.PRODUCT_PRICE_LABEL));
            int stock = Integer.parseInt(body.get(Constants.PRODUCT_STOCK_LABEL));
            Product product = productService.createProduct(body.get(Constants.PRODUCT_NAME_LABEL), body.get(Constants.PRODUCT_DESCRIPTION_LABEL),
                    body.get(Constants.PRODUCT_PART_NO_LABEL), price, stock);

            if (product == null)
                return ResponseEntity.badRequest().body(new CreateProductResult(false, "Error creating the product", null));
            else
                return ResponseEntity.ok().body(new CreateProductResult(true, "Product created", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateProductResult(false, e.getMessage(), null));
        }
    }

    @PutMapping("/product")
    public ResponseEntity<UpdateProductResult> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_NAME_LABEL) || !body.containsKey(Constants.PRODUCT_DESCRIPTION_LABEL)
                || !body.containsKey(Constants.PRODUCT_PART_NO_LABEL) || !body.containsKey(Constants.PRODUCT_PRICE_LABEL)
                || !body.containsKey(Constants.PRODUCT_ID_LABEL))
            return ResponseEntity.badRequest().body(new UpdateProductResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            double price = Double.parseDouble(body.get(Constants.PRODUCT_PRICE_LABEL));
            Product product = productService.updateProduct(id, body.get(Constants.PRODUCT_NAME_LABEL), body.get(Constants.PRODUCT_DESCRIPTION_LABEL),
                    body.get(Constants.PRODUCT_PART_NO_LABEL), price);

            if (product == null)
                return ResponseEntity.badRequest().body(new UpdateProductResult(false, "Error updating the product", null));
            else
                return ResponseEntity.ok().body(new UpdateProductResult(true, "Product updated", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateProductResult(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<DeleteProductResult> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteProductResult(false, "Bad Request"));

        try {
            int productId = Integer.parseInt(id);

            if (productService.deleteProduct(productId))
                return ResponseEntity.ok().body(new DeleteProductResult(true, "Product deleted successfully"));
            else
                return ResponseEntity.badRequest().body(new DeleteProductResult(false, "Error deleting the product"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteProductResult(false, e.getMessage()));
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetProductResult(false, "Bad Request"));

        try {
            int productId = Integer.parseInt(id);

            Product product = productService.getProduct(productId);

            if (product == null)
                return ResponseEntity.badRequest().body(new GetProductResult(false, "Product doesn't exists"));
            else
                return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetProductResult(false, e.getMessage()));
        }
    }

    @PutMapping("/product/stock")
    public ResponseEntity<UpdateProductStockResult> updateStock(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_STOCK_LABEL) || !body.containsKey(Constants.PRODUCT_ID_LABEL))
            return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            int stock = Integer.parseInt(body.get(Constants.PRODUCT_STOCK_LABEL));

            Product product = productService.updateStock(id, stock);

            if (product == null)
                return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, "Error updating the product stock", null));
            else
                return ResponseEntity.ok().body(new UpdateProductStockResult(true, "Product stock updated", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, e.getMessage(), null));
        }
    }

}
