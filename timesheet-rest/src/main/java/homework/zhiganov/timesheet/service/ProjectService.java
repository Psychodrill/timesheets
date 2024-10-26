package homework.zhiganov.timesheet.service;


import java.util.*;

import org.springframework.stereotype.Service;

import homework.zhiganov.aspect.logging.Logging;
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
    @Logging
    public Optional<Project> findById(Long id){
        return pRepository.findById(id);

    }

    public List<Project>findAll(){
        return pRepository.findAll();
    }


    public Project create(Project project){

        return pRepository.save(project);
    }


    public void delete(Long id){
        pRepository.deleteById(id);

    }

    public List<Timesheet> getTimesheetsByProjectId(Long id){
        //throw new UnsupportedOperationException();
        return tsRepository.findByProjId(id);

    }

}
