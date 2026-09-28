package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "Brand_T")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandID")
    private Integer brandID;

    @Column(name = "Name_Brand")
    private String nameBrand;

    @Column(name = "Made")
    private String made;

    @Column(name = "Active")
    private Boolean active;

    // --- Start of Getters and Setters ---

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // --- End of Getters and Setters ---
}