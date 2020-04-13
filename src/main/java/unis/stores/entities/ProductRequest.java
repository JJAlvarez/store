package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "product_request")
public class ProductRequest {

    @Id
    @GenericGenerator(
            name = "idProductRequestSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PRODUCT_REQUEST_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idProductRequestSequenceGenerator")
    private int id;

    @OneToOne
    private Product product;

    @OneToOne
    private Request request;

    @Column(name = "requested_stock")
    private int requestedStock;

    public ProductRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public int getRequestedStock() {
        return requestedStock;
    }

    public void setRequestedStock(int requestedStock) {
        this.requestedStock = requestedStock;
    }
}
