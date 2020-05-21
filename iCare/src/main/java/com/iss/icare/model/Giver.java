package com.iss.icare.model;

import java.sql.Blob;
import java.util.Objects;

public class Giver {
    private int giverId;
    private String giverFirstName;
    private String giverLastName;
    private String giverDescription;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    public Giver(){}

    public Giver(String giverFirstName, String giverDescription, String giverLastName,String username,String phoneNumber,String email,String password) {
        this.giverFirstName = giverFirstName;
        this.giverLastName = giverLastName;
        this.giverDescription = giverDescription;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password=password;
        this.email=email;
    }
    public Giver(int giverId, String giverFirstName, String giverLastName,String giverDescription, String username, String phoneNumber,String email,String password) {
        this(giverFirstName,giverLastName,giverDescription,username,phoneNumber,email,password);
        this.giverId = giverId;
    }

    public String getGiverFirstName() {
        return giverFirstName;
    }

    public void setGiverFirstName(String giverFirstName) {
        this.giverFirstName = giverFirstName;
    }

    public String getGiverLastName() {
        return giverLastName;
    }

    public void setGiverLastName(String giverLastName) {
        this.giverLastName = giverLastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGiverId() { return giverId; }
    public void setGiverId(int giverId) { this.giverId = giverId; }

    public String getGiverDescription() { return giverDescription; }
    public void setGiverDescription(String giverDescription) { this.giverDescription = giverDescription; }

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

    @Override
    public String toString() {
        return "Giver{" +
                "giverId=" + giverId +
                ", giverFirstName='" + giverFirstName + '\'' +
                ", giverLastName='" + giverLastName + '\'' +
                ", giverDescription='" + giverDescription + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giver giver = (Giver) o;
        return giverId == giver.giverId &&
                giverFirstName.equals(giver.giverFirstName) &&
                giverLastName.equals(giver.giverLastName) &&
                Objects.equals(giverDescription, giver.giverDescription) &&
                username.equals(giver.username) &&
                phoneNumber.equals(giver.phoneNumber) &&
                email.equals(giver.email) &&
                password.equals(giver.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giverId, giverFirstName, giverLastName, giverDescription, username, phoneNumber, email, password);
    }
}
