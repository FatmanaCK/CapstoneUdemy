package com.fatmana.capstoneproject.services;


import com.fatmana.capstoneproject.domain.Project;
import com.fatmana.capstoneproject.exceptions.ProjectIdException;
import com.fatmana.capstoneproject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    @Autowired
    //Spring injecting (doing the initializing of the variable) the variable in based on configurations you defined in classes with the @Component annotation
    private ProjectRepository projectRepository;


    public Project saveorUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID" + project.getProjectIdentifier().toUpperCase() + "'already exist");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        //return projectRepository.findByProjectIdentifier(projectId);videoda 8.dk da alttakiyle degistirp devam ettik

        //Only want to return the project if the user looking for it is the owner

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist");

        }
        return project;
    }

    public Iterable<Project>findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier (String projectid){
        Project project=projectRepository.findByProjectIdentifier(projectid);

        if (project==null){
            throw new ProjectIdException("Cannot Project with ID '"+ projectid+" '. This project does not exist");
        }
        projectRepository.delete(project);
    }


}
