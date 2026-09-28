package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "Car_Model_T")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarModel_ID")
    private Integer carModelID;

    @Column(name = "BrandID")
    private Integer brandID;

    @Column(name = "CarName")
    private String carName;

    @Column(name = "Active")
    private Boolean active;

    // --- Start of Getters and Setters ---

    public Integer getCarModelID() {
        return carModelID;
    }

    public void setCarModelID(Integer carModelID) {
        this.carModelID = carModelID;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // --- End of Getters and Setters ---
}