package com.fatmana.capstoneproject.domain;



import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;
//projeyi herseferinde capstoneprojectapplicstion.java dan run edip spring i calistir.
//proje sag click maven >>update project
@Entity //we can create table with attributes
public class Project {
/* private ile table da bulunacak isimleri olusturduktan
 sonra hepsini generate ile getter setter ile get-set method olusturduk.h2 ya baglanmak icin
 webbrowsera git,
 localhost:8080/h2-console yazdiktan sonra database de project create olmus ve tanimladigimiz
 isimleri tableda gormus olacagiz.
 h2 ya bagalnirken problem olustu saniya pom.xml i kendisininkiyle update etti daha sonra
 application.properties da  url i degistirmelisin eger sayfa acilmiyorsa

 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Project name is required")  //bu annotation i h2 yada postmanda null blank gormemek icin kullaniyoruz.
    private String projectName;

    @NotBlank(message = "Project identifier is required")
    @Size(min=4,max=5,message = "Please use 4 to 5 characters")
    @Column(updatable = false,unique = true) //column allows you to set off some parameters for the column itself
    private String projectIdentifier;

    @NotBlank(message = "project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Project (){

}

   @PrePersist // configure a callback for pre-persist (pre-insert) events of the entity.
    protected void onCreate(){
       this.created_At=new Date();

   }

   @PreUpdate
    protected void onUpdate(){
       this.updated_At=new Date();

   }


}