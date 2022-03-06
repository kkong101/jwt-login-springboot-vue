package main.demo.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String myPage() {

        System.out.println("이거 왜 안돌아여?");

        return "서버에서 전송된 My Page 정보 입니다. token 인증이 되어야만 전송됩니다.";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage() {
        return "Admin Page 정보 입니다.";
    }
}
