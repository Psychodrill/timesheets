package homework.zhiganov.timesheet.service;


import java.util.*;

import org.springframework.stereotype.Service;

import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.repository.*;


@Service
public class ProjectService {

    private final ProjectRepository pRepository;
    private final TimesheetRepository tsRepository;

     public ProjectService(ProjectRepository pRepository, TimesheetRepository tsRepository){
         this.pRepository=pRepository;
         this.tsRepository=tsRepository;
     }

    public Optional<Project> getbyId(Long id){
        return pRepository.getbyId(id);

    }

    public List<Project>getAll(){
        return pRepository.getAll();
    }


    public Project create(Project project){

        return pRepository.create(project);
    }


    public void delete(Long id){
        pRepository.delete(id);

    }

    public List<Timesheet> getTimesheetsByProjectId(Long id){
        return tsRepository.getByProjectId(id);

    }

}
