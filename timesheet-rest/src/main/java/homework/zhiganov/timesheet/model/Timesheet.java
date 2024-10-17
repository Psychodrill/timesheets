package homework.zhiganov.timesheet.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "timesheet")
public class Timesheet {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private Long projectId;
    private Long employeeId;
    private Integer minutes;
    private LocalDate createdAt;
    

    // public Timesheet(Long id, String project, int minutes, LocalDate createdAt){

    //     this.setId(id);
    //     this.setProject(project);
    //     this.setMinutes(minutes);
    //     this.setCreatedAt(createdAt);

    // }
}
