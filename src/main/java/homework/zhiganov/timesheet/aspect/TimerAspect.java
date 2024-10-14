package homework.zhiganov.timesheet.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Aspect
@Component
public class TimerAspect {
    
    @Pointcut("@annotation(homework.zhiganov.timesheet.aspect.Timer)")
    public void timerPointcut(){

    }

    @Around(value="timerPointcut()")//Pointcut - точка входа в аспект
    public Object aroundTimesheetServiceFindById(ProceedingJoinPoint pjp)throws Throwable{

        long start = System.currentTimeMillis();
        try{
            return pjp.proceed();
        }
        finally{
            long duration = System.currentTimeMillis()-start;
            log.info("TimesheetService#{} duration = {}ms", pjp.getSignature().getName(), duration);
        }
        // Timesheet ts = new Timesheet();
        // ts.setId(-100L);
        // return Optional.of(ts);

    }

}
