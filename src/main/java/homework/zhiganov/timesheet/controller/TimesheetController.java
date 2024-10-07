package homework.zhiganov.timesheet.controller;


import java.util.*;


import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.service.TimesheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/timesheets")
@Tag(name ="Timesheets", description = "API для работы с таймшитами")
public class TimesheetController {

    
    //@GetMapping("/timesheets/{id}")//get item by id
    //@DeleteMapping("/timesheets/{id}")//delete item by id
    //@PutMapping("/timesheets/{id}")//update item by id

   private final TimesheetService service;

   public TimesheetController(TimesheetService service){
    this.service=service;
   }

    @Operation(
        summary = "Get timesheet",
        description = "Получить таймшит по его идентификатору",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Таймшит не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheet(@PathVariable Long id){
      Optional<Timesheet> ts =  service.findById(id);
        if(ts.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(
        summary = "Get all timesheets",
        description = "Получить все таймшиты",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Таймшиты не найдены",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
        summary = "Create timesheet",
        description = "Создать таймшит",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            //@ApiResponse(description = "Таймшиты не найдены",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    
    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        // Timesheet ts =service.create(timesheet);
        // if(ts==null){
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(timesheet);
        // }
        final Timesheet created = service.create(timesheet);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @Operation(
        summary = "Delete timesheet",
        description = "Удалить таймшит",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Таймшит не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);


        return ResponseEntity.noContent().build();

    }


}
