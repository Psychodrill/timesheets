package homework.zhiganov.timesheet.client;

import lombok.*;
import java.time.*;

@Data
public class TimesheetResponse {


    private Long id;
    private Long projectId;
    private Long employeeId;
    private Integer minutes;
    private LocalDate createdAt;
        

    
}