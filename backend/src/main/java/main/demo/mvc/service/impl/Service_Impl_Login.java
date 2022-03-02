package main.demo.mvc.service.impl;

import main.demo.domain.basement.embed.Password;
import main.demo.domain.basement.type.TokenType;
import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_User;

import main.demo.domain.dto.response.basement.ObjectMessage;
import main.demo.domain.entity.user.B_User;
import main.demo.mvc.repository.Repository_User;
import main.demo.mvc.service.Service_Login;
import main.demo.mvc.service.basement.BaseService;
import main.demo.utilization.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class Service_Impl_Login extends BaseService<Repository_User> implements Service_Login {

    private final Repository_User loginRepository;
    private final JwtToken jwtUtils;

    public Service_Impl_Login(Repository_User loginRepository,JwtToken jwtUtils, EntityManager em) {
        super(loginRepository, em);
        this.loginRepository = loginRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ObjectMessage<Response_User.User> getUser(String accountId) {
        Optional<Response_User.User> result = repository.getUser(accountId);

        // null 처리
        if(result.isEmpty()) {
            return ObjectMessage.<Response_User.User>builder()
                    .status(HttpStatus.OK)
                    .data(null)
                    .build();
        }

        Response_User.User user = result.get();

        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @Override
    public ObjectMessage<Response_User.User> checkUser(String id, String pwd) {
        Optional<Response_User.User> result = repository.getUser(id);

        // null 처리
        if(result.isEmpty()) {
            return ObjectMessage.<Response_User.User>builder()
                    .status(HttpStatus.OK)
                    .data(null)
                    .build();
        }

        Response_User.User user = result.get();

        if (!user.getPassword().isMatched(pwd)) {
            return ObjectMessage.<Response_User.User>builder()
                    .status(HttpStatus.OK)
                    .data(null)
                    .build();
        }


        user.setAccessToken(jwtUtils.generateToken(user.getId(), user.getId(), TokenType.ACCESS_TOKEN));

        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build();
    }

    @Override
    @Transactional
    public ObjectMessage<Response_User.User> addUser(Param_User.Add param) {
        B_User user = B_User.builder()
                .password(param.getUser_pwd())
                .account_id(param.getUser_id())
                .access_token(jwtUtils.generateToken(param.getUser_id(), param.getUser_id(), TokenType.ACCESS_TOKEN))
                .refresh_token(jwtUtils.generateToken(param.getUser_id(), param.getUser_id(), TokenType.REFRESH_TOKEN))
                .build();
        em.persist(user);

        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(Response_User.User.builder()
                        .id(user.getAccount_id())
                        .accessToken(user.getAccess_token())
                        .build()
                )
                .build();
    }

}
