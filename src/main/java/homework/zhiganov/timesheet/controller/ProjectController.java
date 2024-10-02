package homework.zhiganov.timesheet.controller;

import java.util.*;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;


import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.service.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService pService;
    private final TimesheetService tsService;

   public ProjectController(ProjectService pService, TimesheetService tsService){
    this.pService=pService;
    this.tsService = tsService;
   }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id){
        Optional<Project> project =  pService.findById(id);
        if(project.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(project.get());
        }
        return ResponseEntity.notFound().build();


    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.ok(pService.findAll());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
    pService.create(project);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long id){

        ResponseEntity<Project> project =getProject(id);
        if (project == ResponseEntity.notFound()){
            return ResponseEntity.notFound().build();
        }

        
        List<Timesheet> timesheets= pService.getTimesheetsByProjectId(id);
        return ResponseEntity.ok(timesheets);
    }


}
