package homework.zhiganov.timesheet;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import homework.zhiganov.timesheet.model.Project;
import homework.zhiganov.timesheet.model.Timesheet;
import homework.zhiganov.timesheet.repository.ProjectRepository;
import homework.zhiganov.timesheet.repository.TimesheetRepository;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		ProjectRepository projectRepo =ctx.getBean(ProjectRepository.class);
		for(int i=1; i<=5; i++){
			Project project = new Project();
			//project.setId((long)i);
			project.setName("Project #"+i);
			//projectRepo.create(project);
			projectRepo.save(project);
		}
		TimesheetRepository tsr = ctx.getBean(TimesheetRepository.class);
		LocalDate createdAt = LocalDate.now();
		for (int i=1; i<=10; i++){

			createdAt = createdAt.plusDays(1);
			Timesheet timesheet = new Timesheet();
			//timesheet.setId((long)i);
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100,1000));
			tsr.save(timesheet);
		}
	}

}
