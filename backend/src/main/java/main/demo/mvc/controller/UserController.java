package main.demo.mvc.controller;

import main.demo.configuration.property.MyPasswordProperties;
import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_User;
import main.demo.domain.dto.response.basement.ObjectMessage;
import main.demo.mvc.controller.basement.BaseController;
import main.demo.mvc.service.Service_Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController extends BaseController<Service_Login> {

    protected UserController(Service_Login service) {
        super(service);
    }

    @RequestMapping(value="/getUser", method = RequestMethod.POST)
    public @ResponseBody ObjectMessage<Response_User.User> getUser(@RequestBody Param_User.Add param) {
        try {
            return service.getUser(param.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/test", method = RequestMethod.POST)
    public @ResponseBody String test(@RequestBody Param_User.Add param) {
        try {
            System.out.println(param.getUser_id());
            System.out.println(param.getUser_pwd());
//            return service.getUser(param.getUuid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dgdgdg";
    }

    @RequestMapping(value="/checkUser", method = RequestMethod.POST)
    public @ResponseBody ObjectMessage<Response_User.User> checkUser(@RequestBody Param_User.Add param) {
        try {
            return service.checkUser(param.getUser_id(),param.getUser_pwd());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public @ResponseBody ObjectMessage<Response_User.User> addUser(@RequestBody Param_User.Add param) {
        try {
            return service.addUser(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}


