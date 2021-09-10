package com.doeist.SignupValidator;

import com.doeist.Model.Employee.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    /** this method check if the password and confirm
     * password fields are the same
     *
     * @param obj
     * @param context
     * @return boolean
     */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
      Employee employee = (Employee) obj;

      boolean isValid=employee.getPassword().equals(employee.getMatchingPassword());
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "matchingPassword" ).addConstraintViolation();
        }

        return isValid;
    }
}
