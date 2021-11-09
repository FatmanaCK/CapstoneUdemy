package com.fatmana.capstoneproject.repositories;


import com.fatmana.capstoneproject.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository  //bu annotationdan sonra otomatik import eklenmeli
public interface ProjectRepository extends CrudRepository<Project, Long> {
    //sagclick override iterable asagisi otomatik geliyor
   Project findByProjectIdentifier(String projectId);


    @Override
    Iterable<Project> findAll();


}
