package com.fatmana.capstoneproject.services;


import com.fatmana.capstoneproject.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        //@Valid annotation i sonradan ekledik,we're passing a valid request,body of type of project
        /*for me...
        The @Valid annotation ensures the validation of the whole object.
        Importantly, it performs the validation of the whole object graphs.
        However, this creates issues for scenarios needing only partial validation.
        On the other hand, we can use @Validated for group validation,
         including the above partial validation.
         BindingResult is an interface which dictates how the object that stores the result of validation
         should store and retrieve the result of the validation.The BindingResult must come right after the model object that is
         validated or else Spring will fail to validate the object and throw an exception.
         */
        if (result.hasErrors()){
            Map<String,String> errorMAp = new HashMap<>();
            for (FieldError error:result.getFieldErrors()){
                errorMAp.put(error.getField(),error.getDefaultMessage());
            }
            //  return new ResponseEntity<String>("Invalid Project Object",HttpStatus.BAD_REQUEST);// bunu video12deyapti
            //yukaridaki line ile calsitirinca postman error vermiyor.
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        return  null;
}


    public ResponseEntity<?> MapValidationService(BindingResult result) {
        return null; //burayi controller classta erro verdigi icin ekledim.
    }
}
