package homework.zhiganov.timesheet.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Timesheet {
    private Long id;
    private String project;
    private int minutes;
    private LocalDate createdAt;
}
