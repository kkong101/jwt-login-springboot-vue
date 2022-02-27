package main.demo.mvc.controller;

import main.demo.domain.dto.response.basement.ObjectMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/other")
public class OtherController {
    @RequestMapping(value = "/mypage", method = RequestMethod.GET, headers = {"refresh"})
    public @ResponseBody String myPage() {
        return "My Page 정보 입니다.";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET, headers = {"refresh"})
    public @ResponseBody String adminPage() {
        return "Admin Page 정보 입니다.";
    }
}
