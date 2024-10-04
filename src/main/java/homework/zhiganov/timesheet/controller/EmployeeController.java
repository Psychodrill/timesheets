package homework.zhiganov.timesheet.controller;

import java.util.*;
import java.time.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.service.*;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService empService;
    private final TimesheetService tsService;

   public EmployeeController(EmployeeService empService, TimesheetService tsService){
    this.empService=empService;
    this.tsService = tsService;
   }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        Optional<Employee> employee =  empService.findById(id);
        if(employee.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }
        return ResponseEntity.notFound().build();


    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(empService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
    empService.create(employee);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        empService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{localDate}")
    public ResponseEntity<List<Employee>> getEmployeesByEmploymentDate(@PathVariable LocalDate localDate){

        // ResponseEntity<Employee> project =getEmployee(id);
        // if (project == ResponseEntity.notFound()){
        //     return ResponseEntity.notFound().build();
        // }

        return ResponseEntity.ok(empService.findByEmploymentDateAfter(localDate));
    }

}
