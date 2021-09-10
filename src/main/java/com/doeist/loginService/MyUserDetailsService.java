package com.doeist.loginService;

import com.doeist.Model.Employee.Employee;
import com.doeist.Repository.EmployeeServices.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service


public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        EmployeeService employeeService=new EmployeeService();
        Employee employee= employeeService.getByEmail(s);
        MainUser user=new MainUser(employee);
        return user;
    }




}
