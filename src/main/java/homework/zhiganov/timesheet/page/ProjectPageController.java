package homework.zhiganov.timesheet.page;

import java.util.*;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.service.ProjectService;
import homework.zhiganov.timesheet.service.TimesheetPageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {


    private final ProjectService service;


    @GetMapping
    public String getAllProjects(Model model){
         List<Project> projects=service.getAll();
         model.addAttribute("projects", projects);
         return "projects-page.html";
    }
    
    
    
    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable Long id, Model model){
        
        Optional<Project> projectOpt = service.getbyId(id);
        if(projectOpt.isEmpty()){
           // return "not-found.html";
            throw new NoSuchElementException();
        }


        model.addAttribute("projectId", projectOpt.get().getId());
        model.addAttribute("projectName", projectOpt.get().getName());

        return "project-page.html";
    }

}
