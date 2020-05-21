package com.iss.icare.model;

import java.sql.Blob;
import java.util.Objects;

public class Organisation {
    private int ongId;
    private String ongFirstName;
    private String ongLastName;
    private String ongDescription;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;

    public Organisation(){}
    public Organisation(String ongFirstName, String ongLastName,String ongDescription, String phoneNumber, String username, String email,String password) {
        this.ongFirstName = ongFirstName;
        this.ongLastName = ongLastName;
        this.ongDescription = ongDescription;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password=password;
    }
    public Organisation(int ongId, String ongFirstName, String ongLastName,String ongDescription, String phoneNumber, String username, String email,String password) {
        this(ongFirstName,ongLastName,ongDescription,phoneNumber,username, email,password);
        this.ongId = ongId;
    }

    public int getOngId() { return ongId; }
    public void setOngId(int ongId) { this.ongId = ongId; }

    public String getOngDescription() { return ongDescription; }
    public void setOngDescription(String ongDescription) { this.ongDescription = ongDescription; }


    @Override
    public String toString() {
        return "Organisation{" +
                "ongId=" + ongId +
                ", ongFName='" + ongFirstName + '\'' +
                ", ongLName='" + ongLastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ongDescription='" + ongDescription + '\'' +
                ", phone=" + phoneNumber +
                ", username=" + username +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organisation)) return false;
        Organisation that = (Organisation) o;
        return getOngId() == that.getOngId() &&
                getOngFirstName().equals(that.getOngFirstName()) &&
                getOngDescription().equals(that.getOngDescription()) &&
                getUsername().equals(that.getUsername()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOngLastName() {
        return ongLastName;
    }

    public void setOngLastName(String ongLastName) {
        this.ongLastName = ongLastName;
    }

    public String getOngFirstName() {
        return ongFirstName;
    }

    public void setOngFirstName(String ongFirstName) {
        this.ongFirstName = ongFirstName;
    }
}
