package homework.zhiganov.timesheet.service;

import java.util.*;


import org.springframework.stereotype.*;



import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.repository.TimesheetRepository;
@Service
public class TimesheetService {

    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository repository){
        this.repository=repository;
    }

    public Optional<Timesheet> getbyId(Long id){
        return repository.getbyId(id);

    }


    public List<Timesheet>getAll(){
        return repository.getAll();
    }


    public Timesheet create(Timesheet timesheet){

        return repository.create(timesheet);
    }


    public void delete(Long id){
        repository.delete(id);

    }

}
