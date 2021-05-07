package com.example.bankingapi.address;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO) //randomly assigned value
    @Column(name = "ID")
    public Long id;

    @Column(name = "street_number")
    public String street_number;

    @Column(name = "street_name")
    public String street_name;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    @Column(name = "zip")
    public String zip;


    public Address() {
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
