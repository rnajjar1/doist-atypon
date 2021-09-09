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
        System.out.println("whaat is the fuck is this  "+s);
        EmployeeService employeeService=new EmployeeService();
        Employee employee= employeeService.getByEmail(s);
       // System.out.println(employee.getName());
      //  boolean enabled=!user1.isAccountVerified();
     //   MainUser user=new MainUser(user1);
//        UserDetails userDetails= org.springframework.security.core.userdetails.User.
//                withUsername(user1.getEmail()).password(user1.getPassword())
//                .disabled(enabled).authorities("user")
//                .build();
//        return userDetails;




        MainUser user=new MainUser(employee);
        System.out.println(user.getUsername());
        System.out.println("do we reach here");
        return user;
    }




}
