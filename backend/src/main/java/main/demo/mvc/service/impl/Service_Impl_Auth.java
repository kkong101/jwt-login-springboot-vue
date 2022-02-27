package main.demo.mvc.service.impl;

import main.demo.domain.basement.type.TokenType;
import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_Token;
import main.demo.domain.dto.response.Response_User;
import main.demo.domain.dto.response.basement.ObjectMessage;
import main.demo.domain.entity.user.B_User;
import main.demo.mvc.repository.Repository_User;
import main.demo.mvc.service.Service_Auth;
import main.demo.mvc.service.basement.BaseService;
import main.demo.utilization.JwtToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class Service_Impl_Auth extends BaseService<Repository_User> implements Service_Auth {

    private final JwtToken tokenUtils;

    public Service_Impl_Auth(Repository_User repository, EntityManager em, JwtToken tokenUtils) {
        super(repository, em);
        this.tokenUtils = tokenUtils;
    }


    @Override
    public ObjectMessage<Response_Token.Token> createAuthenticationToken(Param_User.Add user) {
        Response_User.User result = repository.getUser(user.getUser_id());
        System.out.println("service");
        String token = tokenUtils.generateToken(result.getId(),result.getId(), TokenType.ACCESS_TOKEN);
        return ObjectMessage.<Response_Token.Token>builder()
                .status(HttpStatus.OK)
                .data(Response_Token.Token.builder().token(token).build())
                .build();
    }
}
