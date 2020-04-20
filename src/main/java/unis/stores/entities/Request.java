package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private int id;

    @Column(name = "request_date")
    private Date date;

    @OneToMany
    @JsonProperty("product")
    private List<ProductRequest> productRequests;

    @OneToOne
    @JsonProperty("factory")
    private Fabric fabric;

    @OneToOne
    @JsonProperty("status")
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

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
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
