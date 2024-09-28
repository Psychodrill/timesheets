package homework.zhiganov.timesheet.controller;


import java.util.*;


import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;


import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.service.TimesheetService;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    
    //@GetMapping("/timesheets/{id}")//get item by id
    //@DeleteMapping("/timesheets/{id}")//delete item by id
    //@PutMapping("/timesheets/{id}")//update item by id

   private final TimesheetService service;

   public TimesheetController(TimesheetService service){
    this.service=service;
   }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheet(@PathVariable Long id){
      Optional<Timesheet> ts =  service.getbyId(id);
        if(ts.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();


    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        Timesheet ts =service.create(timesheet);
        if(ts==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(timesheet);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);


        return ResponseEntity.noContent().build();

    }




}
