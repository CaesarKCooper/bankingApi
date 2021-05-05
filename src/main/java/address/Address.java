package address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Address {

    @Id
    @GeneratedValue //randomly assigned value
    @Column(name="ADDRESS_ID")
    public Long id;

    @Column(name="STREET_NUMBER")
    public String street_number;

    @Column(name="STREET_NAME")
    public String street_name;

    @Column(name="CITY")
    public String city;

    @Column(name="STATE")
    public String state;

    @Column(name="ZIP")
    public String zip;

    public Address() {
    }

    public Address(Long id, String street_number, String street_name, String city, String state, String zip) {
        this.id = id;
        this.street_number = street_number;
        this.street_name = street_name;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
