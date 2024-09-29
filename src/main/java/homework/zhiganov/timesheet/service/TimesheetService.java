package homework.zhiganov.timesheet.service;

import java.time.LocalDate;
import java.util.*;


import org.springframework.stereotype.*;



import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.repository.*;
@Service
public class TimesheetService {

    private final TimesheetRepository tsRepository;
    private final ProjectRepository pRepository;

    public TimesheetService(TimesheetRepository tsRepository, ProjectRepository pRepository){
        this.tsRepository=tsRepository;
        this.pRepository= pRepository;
    }

    public Optional<Timesheet> getbyId(Long id){
        return tsRepository.getbyId(id);

    }


    public List<Timesheet>getAll(){
        return tsRepository.getAll();
    }


    public Timesheet create(Timesheet timesheet){

        // if(pRepository.getbyId(timesheet.getProjectId()).isPresent()){
        //     timesheet.setCreatedAt(LocalDate.now());
        //     return tsRepository.create(timesheet);
        // }
        // return null;
        if(Objects.isNull(timesheet.getProjectId())){
            throw new IllegalArgumentException("projectId must not be null");
        }
        if(pRepository.getbyId(timesheet.getProjectId()).isEmpty()){
            throw new NoSuchElementException("Project with id" + timesheet.getProjectId() + "does not exists");
        }
        timesheet.setCreatedAt(LocalDate.now());
        return tsRepository.create(timesheet);

    }


    public void delete(Long id){
        tsRepository.delete(id);

    }

    public List<Timesheet>findAll() {
        return tsRepository.getAll();
    }

}
