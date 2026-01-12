package com.ipd.demo.dto.request;
import lombok.Getter;
import lombok.Setter;


public class UserCreateRequestDTO {

    private String firstName;
    private String lastName;
    private String email;


    public UserCreateRequestDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
