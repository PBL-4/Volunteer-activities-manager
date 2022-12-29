package com.example.demo1_pbl4.model.dto;


import com.example.demo1_pbl4.utils.PasswordMatches;
import com.example.demo1_pbl4.utils.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatches
public class UserDTO {

    @NotNull
    @NotEmpty(message = "Tên không được để trống")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Tên không được để trống")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNum;

    @ValidEmail(message = "Sai định đạng email")
    @NotNull
    @NotEmpty(message = "Email không được để trống")
    private String email;

    @NotNull
    @NotEmpty(message = "Username không được để trống")
    private String username;

    @NotNull
    @NotEmpty(message = "Password không được để trống")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty(message = "Địa chỉ không được để trống")
    private String address;

    @NotNull
    @NotEmpty(message = "Giới tính không được để trống")
    private String gender;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
