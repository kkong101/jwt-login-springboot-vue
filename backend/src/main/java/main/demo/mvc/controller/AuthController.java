package main.demo.mvc.controller;


import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_Token;
import main.demo.domain.dto.response.basement.ObjectMessage;
import main.demo.mvc.controller.basement.BaseController;
import main.demo.mvc.service.Service_Auth;
import main.demo.mvc.service.Service_Login;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController<Service_Auth> {

    protected AuthController(Service_Auth service) {
        super(service);
    }

    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET, headers = {"refresh"})
    public @ResponseBody ObjectMessage<String> issueRefreshToken(@RequestHeader String refresh) {

        return ObjectMessage.<String>builder()
                .data("ok")
                .build();
    }

    @RequestMapping(value = "/token/access", method = RequestMethod.POST)
    public @ResponseBody ObjectMessage<Response_Token.Token> issueAccessToken(@RequestBody Param_User.Add user) {
        System.out.println("Constroller");
        return service.createAuthenticationToken(user);
    }
}
