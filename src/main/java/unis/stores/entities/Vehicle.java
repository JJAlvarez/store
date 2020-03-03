package unis.stores.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "vehicle")
public class Vehicle {

    @Id
    @Column(name = "universal_code", nullable = false)
    private String universalCode;

    @Column(name = "brand")
    private String brand;

    @Column(name = "line")
    private String line;

    @Column(name = "year")
    private String year;

    public Vehicle() {
    }

    public String getUniversalCode() {
        return universalCode;
    }

    public void setUniversalCode(String universalCode) {
        this.universalCode = universalCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
