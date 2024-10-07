package homework.zhiganov.timesheet.controller;

import java.util.*;
import java.time.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/employees")
@Tag(name ="Employee", description = "API для работы с сотрудниками")
public class EmployeeController {

    private final EmployeeService empService;
    private final TimesheetService tsService;

   public EmployeeController(EmployeeService empService, TimesheetService tsService){
    this.empService=empService;
    this.tsService = tsService;
   }


    @Operation(
        summary = "Get employee",
        description = "Получить сотрудника по его идентификатору",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Сотрудник не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        Optional<Employee> employee =  empService.findById(id);
        if(employee.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }
        return ResponseEntity.notFound().build();


    }


    @Operation(
        summary = "Get all employees",
        description = "Получить всех сотрудников",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Сотрудники не найдены",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(empService.findAll());
    }


    @Operation(
        summary = "Create employee",
        description = "Создать сотрудника",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            //@ApiResponse(description = "Проект не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
    empService.create(employee);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }


    @Operation(
        summary = "Delete employee",
        description = "Удалить сотрудника",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Сотрудник не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        empService.delete(id);
        return ResponseEntity.noContent().build();

    }


    @Operation(
        summary = "Get employees after date",
        description = "Получить сотрудников с датой устройства после указанной",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Сотрудники не найдены",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping("/{localDate}")
    public ResponseEntity<List<Employee>> getEmployeesByEmploymentDate(@PathVariable LocalDate localDate){

        // ResponseEntity<Employee> project =getEmployee(id);
        // if (project == ResponseEntity.notFound()){
        //     return ResponseEntity.notFound().build();
        // }

        return ResponseEntity.ok(empService.findByEmploymentDateAfter(localDate));
    }


    @Operation(
        summary = "Get timesheets by employee id",
        description = "Получить таймшиты по сотрунику",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Сотрудник не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByEmployee(@PathVariable Long employeeId){

        return ResponseEntity.ok(empService.findTimesheetsByEmployeeId(employeeId));
    }

}
