package homework.zhiganov.timesheet.repository;

import java.util.*;
import java.time.*;
import org.springframework.data.jpa.repository.JpaRepository;

import homework.zhiganov.timesheet.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{


    List<Employee> findByFullName(String fullName);

    List<Employee> findByEmploymentDateAfter (LocalDate localDate);
}
