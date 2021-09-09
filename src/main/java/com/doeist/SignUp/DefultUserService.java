package com.doeist.SignUp;

import com.doeist.Model.Employee.Employee;
import com.doeist.Model.Employee.EmployeeBuilder;
import com.doeist.Repository.EmployeeServices.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefultUserService implements UserService{


    EmployeeService employeeService =new EmployeeService();
  //  UserRepository userRepository=new UserRepository();


    @Override
    public void register(Employee employee) throws UserAlreadyExistException {
        if(checkIfUserExist(employee.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Employee employeeEntity = new EmployeeBuilder().getEmployee();
        BeanUtils.copyProperties(employee, employeeEntity);
        encodePassword(employeeEntity, employee);
        employeeService.addNewEmployee(employeeEntity);
      //  userRepository.save(employeeEntity);

    }

    @Override
    public boolean checkIfUserExist(String email) {
        return employeeService.getByEmail(email) !=null ;

        // return userRepository.findByEmail(email) != null;
    }

    private void encodePassword( Employee userEntity, Employee employee){
        userEntity.setPassword(passwordEncoder1().encode(employee.getPassword()));
//        userEntity.setPassword(passwordEncoder.encode(userf.getPassword()));
    }

    public BCryptPasswordEncoder passwordEncoder1(){
      return new BCryptPasswordEncoder();
    }





}
