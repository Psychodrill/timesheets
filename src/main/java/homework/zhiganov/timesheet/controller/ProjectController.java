package homework.zhiganov.timesheet.controller;

import java.util.*;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;


import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/projects")
@Tag(name ="Projects", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService pService;
    private final TimesheetService tsService;

   public ProjectController(ProjectService pService, TimesheetService tsService){
    this.pService=pService;
    this.tsService = tsService;
   }

    @Operation(
        summary = "Get project",
        description = "Получить проект по его идентификатору",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Проект не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        Optional<Project> project =  pService.findById(id);
        if(project.isPresent()){
            //return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(project.get());
        }
        return ResponseEntity.notFound().build();


    }

    @GetMapping
    @Operation(
        summary = "Get all project",
        description = "Получить все проекты",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Проекты не найдены",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    public ResponseEntity<List<Project>> getAll(){
        return ResponseEntity.ok(pService.findAll());
    }

    @PostMapping
    @Operation(
        summary = "Create project",
        description = "Создать проект",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    public ResponseEntity<Project> create(@RequestBody Project project){
    pService.create(project);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("{id}")
    @Operation(
        summary = "Delete project",
        description = "Удалить проект",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Проект не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/timesheets")
    @Operation(
        summary = "Get timesheets by project",
        description = "Получить таймшиты  по проеку",
        responses = {
            @ApiResponse(description = "Успешный ответ", responseCode="200", content =@Content(schema =@Schema(implementation = Project.class))),
            @ApiResponse(description = "Проект не найден",responseCode="404", content =@Content(schema =@Schema(implementation = Void.class))),
            @ApiResponse(description = "Внутренняя ошибка",responseCode="500", content =@Content(schema =@Schema(implementation = Void.class)))       
        }

    )
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long id){

        ResponseEntity<Project> project =getProject(id);
        if (project == ResponseEntity.notFound()){
            return ResponseEntity.notFound().build();
        }

        
        List<Timesheet> timesheets= pService.getTimesheetsByProjectId(id);
        return ResponseEntity.ok(timesheets);
    }


}
