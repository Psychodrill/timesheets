package homework.zhiganov.timesheet;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import homework.zhiganov.timesheet.model.*;

import homework.zhiganov.timesheet.repository.*;


@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		UserRepository userRepository = ctx.getBean(UserRepository.class);
		User admin = new User();
		
		admin.setLogin("admin"); //password
		admin.setPassword("$2a$12$u0k3hBdN9FXhWEMtEReeTeWthvm5H43x4VFQ63WX/hujLFqIQ7806");
		//userRepository.save(user);
		User user = new User();
		
		user.setLogin("user");//userpassword
		user.setPassword("$2a$12$3dTtNyX485tjPJZp.I7yAeBVytb8wlYfiAXO9z7OADKmFSwcuvuzu");


		User anonymous = new User();
		anonymous.setLogin("anon");//anonp
		anonymous.setPassword("$2a$12$UJJTOkYNimQEOJJHI60PPOoKaqog4IFr2gpfBzzZGoIp43mqRi5c.");
		userRepository.save(admin);
		userRepository.save(user);
		userRepository.save(anonymous);
		
		UserRoleRepository userRoleRepository= ctx.getBean(UserRoleRepository.class);
		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setUserId(admin.getId());
		adminAdminRole.setRoleName(Role.ADMIN.getName());
		userRoleRepository.save(adminAdminRole);

		
		UserRole adminUserRole = new UserRole();
		adminUserRole.setUserId(admin.getId());
		adminUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(adminUserRole);

		UserRole userUserRole = new UserRole();
		userUserRole.setUserId(admin.getId());
		userUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(userUserRole);


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
