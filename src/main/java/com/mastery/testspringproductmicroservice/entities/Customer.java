package com.mastery.testspringproductmicroservice.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @NotNull(message = "name cannot be null")
    @Column(length = 14)
    private String name;

    @NotNull(message = "country cannot be null")
    @Column(length = 3)
    private String country;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String phone;

    // one to many relationship with Order table
    // bidirectional relationship increase consistency, therefore, I added it
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
