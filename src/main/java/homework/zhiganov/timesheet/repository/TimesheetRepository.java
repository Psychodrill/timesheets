package homework.zhiganov.timesheet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import homework.zhiganov.timesheet.model.Timesheet;
import java.util.*;
import java.time.*;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long>{


     //select * from timesheet where ptoject_id= 1
    //Note: сломается если в БД результат выдаёт больше одного значения
   // Optional<Timesheet> findByProjectId(Long projectId);
    //select * from timesheet where ptoject_id= 1
    //order_by created_at desc
    List<Timesheet> findByProjectIdOrderByCreatedAtDesc(Long projectId);
    //select * from timesheet where project_id = 1 and minutes =2
    List<Timesheet> findByProjectIdAndMinutes(Long projectId, Integer minutes);
    //select * from timesheet where created_at > 1
    List<Timesheet> findByCreatedAtGreaterThan(LocalDate localDate);
    //select * from timesheet where projectId is null
    List<Timesheet> findByProjectIdIsNull();

    //select * from timesheet where ptoject_id= 1
    //order_by created_at desc
    //jql
    @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjId(Long projectId);
}
