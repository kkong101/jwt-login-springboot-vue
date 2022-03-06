package main.demo.configuration.provider;

import com.google.gson.Gson;
import main.demo.domain.basement.type.ResponseType;
import main.demo.domain.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        System.out.println(authException.getMessage());
        ResponseType responseType = (ResponseType) request.getAttribute("exception");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(new Gson().toJson(new Message(responseType.getCode(), responseType.getMessage())));
//        response.getWriter().flush();
    }
}
