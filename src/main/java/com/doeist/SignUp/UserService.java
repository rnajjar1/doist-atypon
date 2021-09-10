package com.doeist.SignUp;

import com.doeist.Model.Employee.Employee;

public interface UserService {

    void register(Employee employee) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email);

}
