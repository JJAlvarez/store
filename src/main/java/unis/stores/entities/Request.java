package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Request {

    @Id
    @GenericGenerator(
            name = "idRequestSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "REQUEST_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idRequestSequenceGenerator")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_request", joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private List<Product> products;

    @OneToOne
    private Fabric fabric;

    @OneToOne
    private RequestState requestState;

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }
}
