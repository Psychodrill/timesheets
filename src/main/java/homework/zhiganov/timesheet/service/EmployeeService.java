package homework.zhiganov.timesheet.service;

import java.util.*;
import java.time.*;
import org.springframework.stereotype.Service;


import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.repository.*;


@Service
public class EmployeeService {

   
    private final EmployeeRepository empRepository;
    private final TimesheetRepository tsRepository;

    public EmployeeService(EmployeeRepository empRepository, TimesheetRepository tsRepository){
         this.empRepository=empRepository;
         this.tsRepository=tsRepository;
    }

    public Optional<Employee> findById(Long id){
        return empRepository.findById(id);

    }

    public Optional<List<Employee>> findByFullName(String fullName){
         return Optional.of(empRepository.findByFullName(fullName));

    }

    public List<Employee> findAll(){
        return empRepository.findAll();
    }


    public Employee create(Employee employee){

        return empRepository.save(employee);
    }


    public void delete(Long id){
        empRepository.deleteById(id);

    }

    public List<Employee> findByEmploymentDateAfter(LocalDate localDate){
        //throw new UnsupportedOperationException();
        return empRepository.findByEmploymentDateAfter(localDate);

    }

}
