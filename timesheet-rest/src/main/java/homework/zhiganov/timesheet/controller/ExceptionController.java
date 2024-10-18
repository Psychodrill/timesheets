package homework.zhiganov.timesheet.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Works for exceptions in all controllers of package "controllers"
@RestControllerAdvice(basePackageClasses =  ExceptionController.class)
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.internalServerError().body("Something went wrong...");
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handlNoSuchElementException(IllegalArgumentException e){
        return ResponseEntity.notFound().build();
    }
}
