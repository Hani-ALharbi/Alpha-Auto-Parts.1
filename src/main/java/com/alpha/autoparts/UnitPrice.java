package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "Unit Price_T")
public class UnitPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UnitPriceID")
    private Integer unitPriceID;

   //@Column(name = "PartID")
   // private Integer partID;

    @Column(name = "Price")
    private Integer price;

    @Column(name = "Active")
    private Boolean active;

    // --- Start of Getters and Setters ---

    public Integer getUnitPriceID() {
        return unitPriceID;
    }

    public void setUnitPriceID(Integer unitPriceID) {
        this.unitPriceID = unitPriceID;
    }

   // public Integer getPartID() {
    //    return partID;
  //  }

    //public void setPartID(Integer partID) {
       // this.partID = partID;
   // }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // --- End of Getters and Setters ---
}