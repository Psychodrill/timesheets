package homework.zhiganov.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import homework.zhiganov.timesheet.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
