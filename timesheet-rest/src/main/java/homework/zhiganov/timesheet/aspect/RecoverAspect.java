package homework.zhiganov.timesheet.aspect;

import java.lang.annotation.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Defaults;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RecoverAspect {


    @Pointcut("@annotation(homework.zhiganov.timesheet.aspect.Recover)")
    public void recoverPointcut(){}


    @SuppressWarnings("finally")
    @SneakyThrows
    @Around(value="timerPointcut()")//Pointcut - точка входа в аспект
    public Object aroundRecover(ProceedingJoinPoint pjp){

        //Class<? extends Object> clazz =pjp.getSignature().getClass();
       //pjp.getSignature().getDeclaringType()
        try{
            return pjp.proceed();
        }
        catch(Exception ex){
            log.info("Recovering {} after Exception {} with message {}", pjp.getSignature().getName(), ex.getClass(), ex.getMessage());
        }
        finally{
            //Class<? extends Object> clazz =pjp.getSignature().getClass();
            if(!pjp.getSignature().getClass().isPrimitive()){
                return null;
            }else{
                return 0;
            }
            
        }


    }
}
