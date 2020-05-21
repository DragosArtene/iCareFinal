package com.iss.icare.dto;

import java.sql.Blob;
import java.util.Objects;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.*;


@JsonRootName(value = "giver")
@JsonInclude(Include.NON_NULL)
public class GiverDto {
    private int giverId;
    private String giverFirstName;
    private String giverLastName;
    private String giverDescription;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    public GiverDto(){}

    public GiverDto(String giverFirstName, String giverDescription, String giverLastName,String username,String phoneNumber,String email,String password) {
        this.giverFirstName = giverFirstName;
        this.giverLastName = giverLastName;
        this.giverDescription = giverDescription;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password=password;
        this.email=email;
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

    public String getGiverFirstName() {
        return giverFirstName;
    }

    public void setGiverFirstName(String giverFirstName) {
        this.giverFirstName = giverFirstName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GiverDto{" +
                "giverId=" + giverId +
                ", giverFName='" + giverFirstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", giverDescription='" + giverDescription + '\'' +
                ", phone=" + phoneNumber +
                ", username=" + username +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiverDto)) return false;
        GiverDto giverDto = (GiverDto) o;
        return getGiverId() == giverDto.getGiverId() &&
                getGiverFirstName().equals(giverDto.getGiverFirstName()) &&
                getGiverDescription().equals(giverDto.getGiverDescription()) &&
                getUsername().equals(giverDto.getUsername()) &&
                Objects.equals(getPhoneNumber(), giverDto.getPhoneNumber());
    }

    public String getGiverLastName() {
        return giverLastName;
    }

    public void setGiverLastName(String giverLastName) {
        this.giverLastName = giverLastName;
    }
}
