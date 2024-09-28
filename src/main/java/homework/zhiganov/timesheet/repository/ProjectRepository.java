package homework.zhiganov.timesheet.repository;

import java.util.*;


import org.springframework.stereotype.Repository;

import homework.zhiganov.timesheet.model.*;

@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<Project>();


    public Optional<Project> getbyId(Long id){
        //select * from timesheets where id = $id
        // Timesheet ts = new Timesheet(1L, "spring", 73, LocalDate.now());
        // this.timesheets.add(ts);
      return  projects.stream().filter(it-> Objects.equals(it.getId(), id))
        .findFirst();

    }


    public List<Project>getAll(){
        return List.copyOf(projects);
    }


    public Project create(Project project){
        project.setId(sequence++);
        projects.add(project);
        
        return project;
    }


    public void delete(Long id){
        projects.stream().filter(it-> Objects.equals(it.getId(), id))
        .findFirst().ifPresent(projects::remove);

    }

}
