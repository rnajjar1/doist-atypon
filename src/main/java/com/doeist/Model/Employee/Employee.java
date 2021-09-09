package com.doeist.Model.Employee;

import com.SignupValidator.PasswordMatches;
import com.SignupValidator.ValidEmail;

import javax.validation.constraints.NotEmpty;

@PasswordMatches()
public class Employee {

    @NotEmpty
    private String name;

    @NotEmpty
    @ValidEmail
    private String email;

    @NotEmpty
    private String password;
    private String matchingPassword;



    // private int id;



    private Employee(){

    }

    public Employee(String name, String email, String password,String matchingPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.matchingPassword=matchingPassword;
    //    this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }



}
