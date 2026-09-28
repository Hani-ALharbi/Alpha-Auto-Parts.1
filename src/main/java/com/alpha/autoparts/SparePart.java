package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "SPARE_PARTS_T")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartID")
    private Integer partID;

    @Column(name = "PartNumber")
    private Integer partNumber;

    @Column(name = "PartName")
    private String partName;

    // بما أنك تستخدم نفس تصميمك المباشر، بنخلي المفاتيح الأجنبية كأرقام (Integer)
    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "BrandID")
    private Integer brandID;

    @Column(name = "CarModel_ID")
    private Integer carModelID;

    @Column(name = "SupplierID")
    private Integer supplierID;

    @Column(name = "UnitPriceID")
    private Integer unitPriceID;

    @Column(name = "Made")
    private String made;

    @Column(name = "StockQuantity")
    private Integer stockQuantity;

    @Column(name = "ReorderLevel")
    private Integer reorderLevel;

    @Column(name = "Active")
    private Boolean active;

    // --- Start of Getters and Setters ---

    public Integer getPartID() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID = partID;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public Integer getCarModelID() {
        return carModelID;
    }

    public void setCarModelID(Integer carModelID) {
        this.carModelID = carModelID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getUnitPriceID() {
        return unitPriceID;
    }

    public void setUnitPriceID(Integer unitPriceID) {
        this.unitPriceID = unitPriceID;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // --- End of Getters and Setters ---
}