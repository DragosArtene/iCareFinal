package com.iss.icare.dto;
import java.sql.Blob;
import java.util.Objects;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonRootName(value = "ong")
@JsonInclude(Include.NON_NULL)
public class OrganisationDto {
    private int ongId;
    private String ongFirstName;
    private String ongLastName;
    private String ongDescription;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;


    public OrganisationDto(){}
    public OrganisationDto(int ongId,String ongFirstName, String ongLastName,String ongDescription, String phoneNumber, String username, String email,String password) {
        this.ongFirstName = ongFirstName;
        this.ongLastName = ongLastName;
        this.ongDescription = ongDescription;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password=password;
        this.ongId=ongId;
    }

    public int getOngId() { return ongId; }
    public void setOngId(int ongId) { this.ongId = ongId; }

    public String getOngDescription() { return ongDescription; }
    public void setOngDescription(String ongDescription) { this.ongDescription = ongDescription; }

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

    public String getOngFirstName() {
        return ongFirstName;
    }

    public void setOngFirstName(String ongFirstName) {
        this.ongFirstName = ongFirstName;
    }

    public String getOngLastName() {
        return ongLastName;
    }

    public void setOngLastName(String ongLastName) {
        this.ongLastName = ongLastName;
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

    @Override
    public String toString() {
        return "OrganisationDto{" +
                "ongId=" + ongId +
                ", ongFName='" + ongFirstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ongDescription='" + ongDescription + '\'' +
                ", username=" + username +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganisationDto)) return false;
        OrganisationDto that = (OrganisationDto) o;
        return getOngId() == that.getOngId() &&
                getOngFirstName().equals(that.getOngFirstName()) &&
                getOngDescription().equals(that.getOngDescription()) &&
                getUsername().equals(that.getUsername()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

}
