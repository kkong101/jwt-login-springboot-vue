package main.demo.configuration.provider;

import io.jsonwebtoken.Claims;
import main.demo.domain.basement.type.ResponseType;
import main.demo.domain.basement.type.TokenType;
import main.demo.domain.dto.response.Response_User;
import main.demo.domain.entity.user.B_User;
import main.demo.mvc.repository.Repository_User;
import main.demo.utilization.JwtToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtToken tokenUtils;
    private final Repository_User userRepository;

    public JWTAuthenticationFilter(JwtToken tokenUtils, Repository_User userRepository) {
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println(request.getServletPath());
        try  {
            if (authorization != null && authorization.startsWith("Bearer ")) {

                // "Bearer " 부분 잘라냄.
                String token = authorization.substring(7);

                Claims claims = tokenUtils.parse(token).getBody();

                String tokenType = (String) claims.get("type");
                String id = (String) claims.get("id");
                String name = (String) claims.get("name");

                if("access".equals(tokenType) && !"/user/reissue/token".equals(request.getServletPath())) {
                    request.setAttribute("userId", id);
                    request.setAttribute("name",name);

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(id,null,null));

                    filterChain.doFilter(request,response);

                } else if ("refresh".equals(tokenType) && "/user/reissue/token".equals(request.getServletPath())) {
                    Response_User.User user = userRepository.getUser(id);
                    if (user == null) throw new Exception();

                    // DB에 저장된 Refresh 토큰과 일치하다면 access 토큰 발급
                    if(token.equals(user.getRefreshToken())) {
                        String access = tokenUtils.generateToken(user.getId(),user.getId(), TokenType.ACCESS_TOKEN);
                        request.setAttribute("access",access);

                        // refresh 토큰 만기 시 다시 발급.
                        if(tokenUtils.compareTo(claims.getExpiration())) {
                            String refresh = tokenUtils.generateToken(user.getId(),user.getId(),TokenType.ADDITION_REFRESH_TOKEN);

                            userRepository.updateToken(user.getId(),refresh,access);

                            request.setAttribute("refresh", refresh);

                            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getId(),null,null));

                            filterChain.doFilter(request,response);
                        }
                    }
                }

            }
        } catch (Exception e) {
            request.setAttribute("exception", ResponseType.SEVER_ERROR);
            filterChain.doFilter(request,response);
        }
    }
}
