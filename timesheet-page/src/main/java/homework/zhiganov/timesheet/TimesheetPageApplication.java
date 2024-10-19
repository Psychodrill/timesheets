package homework.zhiganov.timesheet;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;



@EnableDiscoveryClient
@SpringBootApplication
public class TimesheetPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetPageApplication.class,args);
		
	}

}
