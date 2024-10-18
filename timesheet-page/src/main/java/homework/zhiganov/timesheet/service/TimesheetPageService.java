package homework.zhiganov.timesheet.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import homework.zhiganov.timesheet.client.ProjectResponse;
import homework.zhiganov.timesheet.client.TimesheetResponse;
//import homework.zhiganov.timesheet.model.Project;
//import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.page.TimesheetPageDTO;
import lombok.RequiredArgsConstructor;

@Service

public class TimesheetPageService {

    private final RestClient restClient;
    public TimesheetPageService(){
        this.restClient = RestClient.create("http://localhost:8080");
    }

    public Optional<TimesheetPageDTO> getbyId(Long id){
        try{
            TimesheetResponse timesheet = restClient.get()
            .uri("/timesheets/" + id).retrieve().body(TimesheetResponse.class);

            TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
            timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
            timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

            ProjectResponse project =restClient.get().uri("/projects/"+ timesheet.getProjectId()).retrieve().body(ProjectResponse.class);
            timesheetPageDTO.setProjectName(project.getName());
            return Optional.of(timesheetPageDTO);

        }catch(HttpClientErrorException.NotFound e){
            return Optional.empty();
        }

    }
    public List<TimesheetPageDTO> findAll() {
        List<TimesheetResponse> timesheets = restClient.get()
                .uri("/timesheets")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {
        });
        
        List<TimesheetPageDTO> result = new ArrayList();
        for(TimesheetResponse timesheet:timesheets){
            TimesheetPageDTO timesheetPageDTO = new TimesheetPageDTO();
            timesheetPageDTO.setId(String.valueOf(timesheet.getId()));
            timesheetPageDTO.setMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDTO.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));

            ProjectResponse project =restClient.get().uri("/projects/"+ timesheet.getProjectId()).retrieve().body(ProjectResponse.class);
            timesheetPageDTO.setProjectName(project.getName());
            result.add(timesheetPageDTO);
        }
        
        return result;
    }

}
