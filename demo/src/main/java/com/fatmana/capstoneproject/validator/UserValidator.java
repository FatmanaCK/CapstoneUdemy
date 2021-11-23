package com.fatmana.capstoneproject.validator;


import com.fatmana.capstoneproject.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
        //User domain folderda user class

    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        if(user.getPassword().length() <6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            //(should be used equals not use == when run the test you take error)
            errors.rejectValue("confirmPassword","Match", "Passwords must match");

        }

    }
}