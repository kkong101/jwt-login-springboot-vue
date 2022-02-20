package main.demo.mvc.service.impl;

import main.demo.domain.dto.request.Param_User;
import main.demo.domain.dto.response.Response_User;

import main.demo.domain.dto.response.basement.ObjectMessage;
import main.demo.domain.entity.user.B_User;
import main.demo.mvc.repository.Repository_User;
import main.demo.mvc.service.Service_Login;
import main.demo.mvc.service.basement.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class Service_Impl_Login extends BaseService<Repository_User> implements Service_Login {

    private final Repository_User loginRepository;

    public Service_Impl_Login(Repository_User loginRepository, EntityManager em) {
        super(loginRepository, em);
        this.loginRepository = loginRepository;
    }

    @Override
    public ObjectMessage<Response_User.User> getUser(String uuid) {
        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(repository.getUser(uuid))
                .build();
    }

    @Override
    public ObjectMessage<Response_User.User> checkUser(String id, String pwd) {
        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(repository.checkUser(id,pwd))
                .build();
    }

    @Override
    @Transactional
    public ObjectMessage<Response_User.User> addUser(Param_User.Add param) {
        B_User user = B_User.builder()
                .password(param.getUser_pwd())
                .account_id(param.getUser_id())
                .build();

        em.persist(user);

        return ObjectMessage.<Response_User.User>builder()
                .status(HttpStatus.OK)
                .data(Response_User.User.builder()
                        .build()
                )
                .build();
    }

}
