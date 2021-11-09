package com.fatmana.capstoneproject.web;

import com.fatmana.capstoneproject.domain.Project;
import com.fatmana.capstoneproject.services.MapValidationErrorService;
import com.fatmana.capstoneproject.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin        //bunu 32.videoda vscode error verince ekledik
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService; //mapvalidtionerrorservice classtan sonra ekledik bunu

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
      /*  if (result.hasErrors()){
            Map<String,String> errorMAp = new HashMap<>();
            for (FieldError error:result.getFieldErrors()){
                errorMAp.put(error.getField(),error.getDefaultMessage());
            }
            burayi 14.videoda kesip mapvalidationerrorservice class a yapistirdik.

         //  return new ResponseEntity<String>("Invalid Project Object",HttpStatus.BAD_REQUEST);// bunu video12deyapti
          //yukaridaki line ile calsitirinca postman error vermiyor.
           return new ResponseEntity<List<FieldError>>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
        }

       */

        ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationService(result);
        if(errorMap !=null)return errorMap;


        Project project1=projectService.saveorUpdateProject(project); //eger burayi yazmazsak h2console ve postman run etmiyor
        return new ResponseEntity<Project>(project, HttpStatus.CREATED); //we should be enabled to start actually using our API and creating object

    }

    @GetMapping("/{projectId}") //parantez icine we need to pass a path variable double quote,icindeki asagidaki path variable ile match olmali
    public ResponseEntity<?> getProjectId(@PathVariable String projectId ){
                                    //paranteze path annnaotation and path variable

        Project project =projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping ("/all") //video17
    public Iterable<Project> getAllProjects(){return projectService.findAllProjects();}
    //we don't need any exception now


    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String >("Project with ID: '" +projectId+ "'was deleted",HttpStatus.OK);
    }
}
