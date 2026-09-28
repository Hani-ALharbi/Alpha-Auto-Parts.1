package com.alpha.autoparts;

import jakarta.persistence.*;
import java.sql.Date; // استخدمنا هذا عشان يقبل التاريخ من الواجهة مباشرة

@Entity
@Table(name = "ORDERS_T")
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderID;

    @Column(name = "OrderDate")
    private Integer orderDate;

    @Column(name = "CustomerID")
    private Integer customerID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "PartID")
    private Integer partID;

    @Column(name = "UnitPriceID")
    private Integer unitPriceID;

    @Column(name = "Status")
    private Boolean status;

    // --- Start of Getters and Setters ---

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Integer orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getPartID() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID = partID;
    }

    public Integer getUnitPriceID() {
        return unitPriceID;
    }

    public void setUnitPriceID(Integer unitPriceID) {
        this.unitPriceID = unitPriceID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    // --- End of Getters and Setters ---
}