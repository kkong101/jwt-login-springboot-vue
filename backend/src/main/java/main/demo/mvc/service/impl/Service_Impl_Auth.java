package main.demo.mvc.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.util.Optional;

@Service
public class Service_Impl_Auth extends BaseService<Repository_User> implements Service_Auth {

    private final JwtToken tokenUtils;

    public Service_Impl_Auth(Repository_User repository, EntityManager em, JwtToken tokenUtils) {
        super(repository, em);
        this.tokenUtils = tokenUtils;
    }

    @Override
    public ObjectMessage<Response_Token.Token> createAuthenticationToken(Param_User.Add param) {
        // Access 토큰을 재발급 받기 위해 검증을 진행
        // Front에 있는 사용자 정보를 디비에서 조회함.
        Optional<Response_User.User> result = repository.getUser(param.getUser_id());

        if(result == null) {
            return ObjectMessage.<Response_Token.Token>builder()
                    .status(HttpStatus.OK)
                    .data(null)
                    .build();
        }

        Response_User.User user = result.get();

        // 해당 정보유저의 refresh 토클을 가져와서
        // 파싱을 진행해서 해당 유저가 맞는지 확인한다.
        try {
            String refreshToken = user.getRefreshToken();
            Claims claims = tokenUtils.parse(refreshToken);
            String id = (String) claims.get("id");
            String name = (String) claims.get("name");


            // refresh 있는 토큰의 아이디, 이름이 일치 하다면, 토큰 발행
            if(param.getUser_id() == id && param.getUser_name() ==  name) {

                String token = tokenUtils.generateToken(user.getId(),user.getId(), TokenType.ACCESS_TOKEN);
                return ObjectMessage.<Response_Token.Token>builder()
                        .status(HttpStatus.OK)
                        .data(Response_Token.Token.builder().token(token).build())
                        .build();
            } else {
                // 일치하지 않다면 => 변조된 토큰이라면
            }


        } catch(ExpiredJwtException e) {
            // refresh 토큰 만기 시 다시 발급
            String token = tokenUtils.generateToken(user.getId(),user.getId(), TokenType.REFRESH_TOKEN);
            long updated = repository.updateRefreshToken(param.getUser_id(),token);

            System.out.println(updated);

        } catch (Exception e) {

        }


        return null;
    }


}
