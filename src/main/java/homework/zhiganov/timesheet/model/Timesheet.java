package homework.zhiganov.timesheet.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Timesheet {
    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDate createdAt;

    // public Timesheet(Long id, String project, int minutes, LocalDate createdAt){

    //     this.setId(id);
    //     this.setProject(project);
    //     this.setMinutes(minutes);
    //     this.setCreatedAt(createdAt);

    // }
}
