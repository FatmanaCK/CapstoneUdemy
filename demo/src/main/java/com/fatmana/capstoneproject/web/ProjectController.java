package com.fatmana.capstoneproject.web;

import com.fatmana.capstoneproject.domain.Project;
import com.fatmana.capstoneproject.sevices.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("") //this pulse mapping annotation is going to return a response entity of type project
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
           return new ResponseEntity<List<FieldError>>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
        }
        Project project1=projectService.saveorUpdateProject(project); //eger burayi yazmazsak h2console ve postman run etmiyor
        return new ResponseEntity<Project>(project, HttpStatus.CREATED); //we should be enabled to start actually using our API and creating object

    }


}
