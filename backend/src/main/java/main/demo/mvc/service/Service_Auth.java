package main.demo.mvc.service;

import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_Token;
import main.demo.domain.dto.response.basement.ObjectMessage;

public interface Service_Auth {
    public ObjectMessage<Response_Token.Token> createAuthenticationToken(Param_User.Add user);
}
