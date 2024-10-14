package homework.zhiganov.timesheet.aspect;

import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import homework.zhiganov.timesheet.model.Timesheet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    //Before
    //After
    //AfterThrowing
    //AfterReturning
    //Around

    @Pointcut("execution(* homework.zhiganov.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut(){

    }
    
    @Before(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    public void beforeTimesheetServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
       // Long id = (Long) jp.getArgs()[0];
        log.info("Before -> TimesheetService#{}", methodName);

    }

    @After(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    public void afterTimesheetServiceFindById(JoinPoint jp){
        String methodName = jp.getSignature().getName();
       // Long id = (Long) jp.getArgs()[0];
        log.info("After -> TimesheetService#{}", methodName);

    }

    @Around(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    public Object aroundTimesheetServiceFindById(ProceedingJoinPoint pjp)throws Throwable{

        // Timesheet ts = new Timesheet();
        // ts.setId(-100L);
        // return Optional.of(ts);
        
        return  pjp.proceed();

    }

}
