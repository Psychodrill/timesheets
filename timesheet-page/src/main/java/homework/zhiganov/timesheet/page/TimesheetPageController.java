package homework.zhiganov.timesheet.page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import homework.zhiganov.timesheet.model.Project;
//import homework.zhiganov.timesheet.model.Timesheet;
//import homework.zhiganov.timesheet.service.ProjectService;
import homework.zhiganov.timesheet.service.TimesheetPageService;
//import homework.zhiganov.timesheet.service.TimesheetService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService service;


    @GetMapping
    public String getAllTimeSheets(Model model){
        List<TimesheetPageDTO> timesheets=service.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }
    
    
    
    @GetMapping("/{id}")
    public String getTimesheetPage(@PathVariable Long id, Model model){
        
        Optional<TimesheetPageDTO> timesheetOpt = service.getbyId(id);
        if(timesheetOpt.isEmpty()){
           // return "not-found.html";
            throw new NoSuchElementException();
        }


        model.addAttribute("timesheet", timesheetOpt.get());

        return "timesheet-page.html";
    }

}
