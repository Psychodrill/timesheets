package homework.zhiganov.aspect.logging;


import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
//import homework.zhiganov.timesheet.model.Timesheet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
//@Component
@RequiredArgsConstructor
public class LoggingAspect {

    //Before
    //After
    //AfterThrowing
    //AfterReturning
    //Around

    // @Pointcut("execution(* homework.zhiganov.timesheet.service.TimesheetService.*(..))")
    // public void timesheetServiceMethodsPointcut(){

    // }
    
    // @Before(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    // public void beforeTimesheetServiceFindById(JoinPoint jp){
        
    //     //String methodName = jp.getSignature().getName();
    //     Object[] arr =jp.getArgs();
    //     arr[0].getClass();
    //    // Long id = (Long) jp.getArgs()[0];
    //     log.info("Before -> TimesheetService#{} = {}", arr[0].getClass(), arr[0]);

    // }

    // @After(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    // public void afterTimesheetServiceFindById(JoinPoint jp){
    //     String methodName = jp.getSignature().getName();
    //    // Long id = (Long) jp.getArgs()[0];
    //     log.info("After -> TimesheetService#{}", methodName);

    // }

    // @Around(value="timesheetServiceMethodsPointcut()")//Pointcut - точка входа в аспект
    // public Object aroundTimesheetServiceFindById(ProceedingJoinPoint pjp)throws Throwable{

    //     log.info("Around -> TimesheetService#{} ={}",List.of(pjp.getArgs().getClass()) , List.of(pjp.getArgs()));
    //     return  pjp.proceed();

    // }

    private final LoggingProperties properties;

    @Pointcut("@annotation(homework.zhiganov.timesheet.aspect.Logging)")
    public void loggingMethodsPointcut(){

    }
    @Pointcut("@within(homework.zhiganov.timesheet.aspect.Logging)")
    public void loggingTypePointcut(){

    }
    @Around(value="loggingMethodsPointcut() || loggingTypePointcut()")//Pointcut - точка входа в аспект
    public Object loggingMethod(ProceedingJoinPoint pjp)throws Throwable{

        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        try{
            return pjp.proceed();
        }
        finally{
            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
        }

     }

}
