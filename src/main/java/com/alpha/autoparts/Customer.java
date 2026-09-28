package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "CUSTOMERS_T")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Type")
    private String type;

    @Column(name = "Phone")
    private Integer phone;

    @Column(name = "City")
    private String city;

    // --- Start of Getters and Setters ---

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    // --- End of Getters and Setters ---
}