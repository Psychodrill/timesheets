package homework.zhiganov.aspect.recover;

import java.lang.annotation.*;

import org.aspectj.lang.annotation.Pointcut;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Recover {


}
