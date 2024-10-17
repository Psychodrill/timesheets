package homework.zhiganov.timesheet.aspect;

import java.lang.annotation.*;

import org.aspectj.lang.annotation.Pointcut;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Recover {


}
