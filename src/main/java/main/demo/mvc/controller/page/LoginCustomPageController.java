package main.demo.mvc.controller.page;

import main.demo.configuration.property.MyPasswordProperties;
import main.demo.utilization.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginCustomPageController {

    @GetMapping("/")
    public String welcome(){
        return "출력 되나?";
    }

    @GetMapping("/api/hello")
    public String hello(){
        return "안녕하세요. 현재 서버시간은 "+ new Date() +"입니다. \n";
    }

    @GetMapping("/test")
    public String test22(){
        return "test";
    }
}
