package com.alpha.autoparts;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS_T")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Staff_ID", nullable = false)
    private Integer staffID;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash; // أصبحت بدون قيمة افتراضية

    @Column(name = "TypeUser")
    private String typeUser;

    @Column(name = "PhoneNumber")
    private Integer phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Nationality")
    private String nationality;

    @Column(name = "National_ID")
    private Integer nationalID;

    @Column(name = "DOB")
    private Integer DOB;
    
    @Column(name = "Active")
    private Boolean active = true;

    // --- Getters and Setters (تأكد من وجودها كما في السابق) ---



    // --- Start of Getters and Setters ---

    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Integer getStaffID() { return staffID; }
    public void setStaffID(Integer staffID) { this.staffID = staffID; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getTypeUser() { return typeUser; }
    public void setTypeUser(String typeUser) { this.typeUser = typeUser; }

    public Integer getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Integer phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Integer getNationalID() { return nationalID; }
    public void setNationalID(Integer nationalID) { this.nationalID = nationalID; }

    public Integer getDob() { return DOB; }
    public void setDob(Integer dob) { this.DOB = dob; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    // --- End of Getters and Setters ---
}

