package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "CATEGORIES_T") // استخدمنا الاسم اللي طلبته
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryID;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Active")
    private Boolean active;

    // --- Start of Getters and Setters ---

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    // --- End of Getters and Setters ---
}