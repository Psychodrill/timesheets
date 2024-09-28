package homework.zhiganov.timesheet.controller;

import lombok.Data;


@Data
public class TimesheetPageDTO {

    private String projectName;
    private String id;
    private String minutes;
    private String createdAt;

}
