package homework.zhiganov.timesheet.repository;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.*;

import homework.zhiganov.timesheet.model.Timesheet;

@Repository
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<Timesheet>();


    public Optional<Timesheet> getbyId(Long id){
        //select * from timesheets where id = 
        // Timesheet ts = new Timesheet(1L, "spring", 73, LocalDate.now());
        // this.timesheets.add(ts);
      return  timesheets.stream().filter(it-> Objects.equals(it.getId(), id))
        .findFirst();

    }


    public List<Timesheet>getAll(){
        return List.copyOf(timesheets);
    }


    public Timesheet create(Timesheet timesheet){
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        
        return timesheet;
    }


    public void delete(Long id){
        timesheets.stream().filter(it-> Objects.equals(it.getId(), id))
        .findFirst().ifPresent(timesheets::remove);

    }

    public List<Timesheet> getByProjectId(Long id){
        //select * from timesheets where id = 
        // Timesheet ts = new Timesheet(1L, "spring", 73, LocalDate.now());
        // this.timesheets.add(ts);
      //return List.copyOf(timesheets.stream().filter(it-> Objects.equals(it.getProjectId(), id)));
      return timesheets.stream().filter(it-> Objects.equals(it.getProjectId(), id)).toList();
    }




}
