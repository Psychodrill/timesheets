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
		anonymous.setLogin("anon");//anonpas
		anonymous.setPassword("$2a$12$FDLBm0gFuOK6n.IQ8kfYE.BdIEy5kKgMNDkb0VJg/CbYbM.nxdpHu");
		userRepository.save(admin);
		userRepository.save(user);
		userRepository.save(anonymous);
		
		RoleRepository roleRepository= ctx.getBean(RoleRepository.class);
		Role roleAdmin = new Role();
		//role.setUserId(admin.getId());
		roleAdmin.setName("ADMIN");
		roleRepository.save(roleAdmin);
		Role roleUser = new Role();
		//role.setUserId(admin.getId());
		roleUser.setName("USER");
		roleRepository.save(roleUser);
		Role roleRest = new Role();
		//role.setUserId(admin.getId());
		roleRest.setName("REST");
		roleRepository.save(roleRest);


		UserRoleRepository userRoleRepository= ctx.getBean(UserRoleRepository.class);
		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setUserId(admin.getId());
		adminAdminRole.setRoleId(roleAdmin.getId());
		userRoleRepository.save(adminAdminRole);

		
		UserRole adminUserRole = new UserRole();
		adminUserRole.setUserId(admin.getId());
		adminUserRole.setRoleId(roleUser.getId());
		userRoleRepository.save(adminUserRole);

		UserRole userUserRole = new UserRole();
		userUserRole.setUserId(user.getId());
		userUserRole.setRoleId(roleUser.getId());
		userRoleRepository.save(userUserRole);

		UserRole anonRestRole = new UserRole();
		anonRestRole.setUserId(anonymous.getId());
		anonRestRole.setRoleId(roleRest.getId());
		userRoleRepository.save(anonRestRole);


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
