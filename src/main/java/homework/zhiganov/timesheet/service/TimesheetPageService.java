package homework.zhiganov.timesheet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import homework.zhiganov.timesheet.controller.TimesheetPageDTO;
import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.model.Timesheet;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {


    private final TimesheetService timesheetService;
    private final ProjectService projectService;
    public Optional<TimesheetPageDTO> getbyId(Long id){

       return timesheetService.getbyId(id).map(this::convert);

    }

    private TimesheetPageDTO convert(Timesheet timesheet){

        Project project =projectService.getbyId(timesheet.getProjectId()).orElseThrow();

        TimesheetPageDTO timesheetPageDTO= new TimesheetPageDTO();

        timesheetPageDTO.setProjectName(project.getName());
        timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
        timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().toString());
        return timesheetPageDTO;


    }
    public List<TimesheetPageDTO> findAll() {

        return timesheetService.findAll().stream().map(this::convert).toList();
        //return new ArrayList<TimesheetPageDTO>();
    }

}
