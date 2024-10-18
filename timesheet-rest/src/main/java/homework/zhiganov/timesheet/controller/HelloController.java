package homework.zhiganov.timesheet.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloController {

    @GetMapping("/hello")
    //@ResponseBody
    public String helloPage(@RequestParam(required = false) String username){
        //String username = "Taras";
        return "<h1>Hello, "+username+" <h1>";
    }

}
