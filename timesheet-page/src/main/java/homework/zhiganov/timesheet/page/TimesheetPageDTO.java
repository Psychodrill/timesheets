package homework.zhiganov.timesheet.page;

import lombok.Data;


@Data
public class TimesheetPageDTO {


    private String id;
    private String projectId;
    private String projectName;
    private String minutes;
    private String createdAt;

}
