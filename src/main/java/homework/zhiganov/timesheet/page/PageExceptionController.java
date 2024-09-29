package homework.zhiganov.timesheet.page;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice(basePackageClasses = PageExceptionController.class)
public class PageExceptionController {

    @GetMapping("/home/oops")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String oopsPage(){
        return "oops.html";
    }


    // @ExceptionHandler(NoSuchElementException.class)
    // public String handleNoSuchElementException(NoSuchElementException e){
    //     return "not-found.html";
    // }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        return "redirect:/home/oops";
    }

}
