package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Fabric {

    @Id
    @GenericGenerator(
            name = "idFabricSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "FABRIC_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idFabricSequenceGenerator")
    @JsonProperty("id")
    private int id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "ip")
    @JsonProperty("ip")
    private String ip;

    @Column(name = "service_password")
    @JsonProperty("servicePassword")
    private String servicePassword;

    @Column(name = "last_date_request")
    @JsonProperty("lastDateRequest")
    private Date lastDateRequest;

    public Fabric() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    public Date getLastDateRequest() {
        return lastDateRequest;
    }

    public void setLastDateRequest(Date lastDateRequest) {
        this.lastDateRequest = lastDateRequest;
    }
}
