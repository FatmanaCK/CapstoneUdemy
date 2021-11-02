package com.fatmana.capstoneproject.sevices;


import com.fatmana.capstoneproject.domain.Project;
import com.fatmana.capstoneproject.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired //Spring injecting (doing the initializing of the variable) the variable in based on configurations you defined in classes with the @Component annotation
    private ProjectRepository projectRepository;


    public Project saveorUpdateProject(Project project) {

        //Logic
        return projectRepository.save(project);
    }
}
