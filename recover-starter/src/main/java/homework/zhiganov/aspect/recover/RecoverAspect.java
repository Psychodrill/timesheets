package homework.zhiganov.aspect.recover;

import java.lang.annotation.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Defaults;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Aspect
@Component
@RequiredArgsConstructor
public class RecoverAspect {


    private final Boolean enabled;

    @Pointcut("@annotation(homework.zhiganov.aspect.recover.Recover)")
    public void recoverPointcut(){}


    @SuppressWarnings("finally")
    @SneakyThrows
    @Around(value="recoverPointcut()")//Pointcut - точка входа в аспект
    public Object aroundRecover(ProceedingJoinPoint pjp){

        if(enabled){

            try{
                return pjp.proceed();
            }
            catch(Exception ex){
                log.info("Recovering {} after Exception {} with message {}", pjp.getSignature().getName(), ex.getClass(), ex.getMessage());
            }
            finally{
                //Class<? extends Object> clazz =pjp.getSignature().getClass();
                if(pjp.getSignature().getClass().isPrimitive()){
                    return 0;
                }else{
                    return null;
                }
                
            }
        }
        else return null;
    }
}
