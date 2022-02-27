package main.demo.configuration.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import main.demo.domain.basement.type.ResponseType;
import main.demo.domain.basement.type.TokenType;
import main.demo.domain.dto.response.Response_User;
import main.demo.domain.entity.user.B_User;
import main.demo.mvc.repository.Repository_User;
import main.demo.utilization.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Optional;


@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtToken tokenUtils;
    private final Repository_User userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        System.out.println(request.getServletPath());

        try {
            String token22 = authorization.substring(7);
            Claims claims22 = tokenUtils.parse(token22);
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", ResponseType.TOKEN_TIME_OUT);
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            request.setAttribute("exception", ResponseType.SEVER_ERROR);
            filterChain.doFilter(request, response);
        }


//        try  {
//
//            if (authorization != null && authorization.startsWith("Bearer ")) {
//                // "Bearer " 부분 잘라냄.
//
//                String token = authorization.substring(7);
//                Claims claims = tokenUtils.parse(token);
//                String tokenType = (String) claims.get("type");
//                String id = (String) claims.get("id");
//                String name = (String) claims.get("name");
//                Date exp = (Date) claims.get("exp");
//                System.out.println("정상 접근");
//
//                // 엑세스 토큰 재발급
//                if("/auth/token/access".equals((request.getServletPath()))) {
//                    System.out.println(id);
//                    Response_User.User user = userRepository.getUser(id);
//                    System.out.println(user.getId());
//                    System.out.println(tokenUtils.isExpired(exp));
//                    if (user == null && tokenUtils.isExpired(exp)) throw new Exception();
//                    request.setAttribute("access",tokenUtils.generateToken(user.getId(),user.getId(),TokenType.ACCESS_TOKEN));
//
//                } else if("access".equals(tokenType) && !tokenUtils.isExpired(exp) && !"/auth/token/refresh".equals(request.getServletPath())) {
//                    request.setAttribute("userId", id);
//                    request.setAttribute("name",name);
//
//                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(id,null,null));
//
//                    filterChain.doFilter(request,response);
//
//                } else if ("refresh".equals(tokenType) && "/auth/token/refresh".equals(request.getServletPath())) {
//                    Response_User.User user = userRepository.getUser(id);
//                    if (user == null) throw new Exception();
//
//                    // DB에 저장된 Refresh 토큰과 일치하다면 access 토큰 발급
//                    if(token.equals(user.getRefreshToken())) {
//                        String access = tokenUtils.generateToken(user.getId(),user.getId(), TokenType.ACCESS_TOKEN);
//                        request.setAttribute("access",access);
//
//                        // refresh 토큰 만기 시 다시 발급.
//                        if(tokenUtils.compareTo(claims.getExpiration())) {
//                            String refresh = tokenUtils.generateToken(user.getId(),user.getId(),TokenType.ADDITION_REFRESH_TOKEN);
//
//                            userRepository.updateToken(user.getId(),refresh,access);
//
//                            request.setAttribute("refresh", refresh);
//
//                            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getId(),null,null));
//
//                            filterChain.doFilter(request,response);
//                        }
//                    }
//                }
//
//            }
//        } catch (ExpiredJwtException e) {
//            request.setAttribute("exception", ResponseType.TOKEN_TIME_OUT);
//            filterChain.doFilter(request,response);
//        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
//            request.setAttribute("exception", ResponseType.TOKEN_INVALID);
//            filterChain.doFilter(request,response);
//        } catch (Exception e) {
//            request.setAttribute("exception", ResponseType.SEVER_ERROR);
//            filterChain.doFilter(request, response);
//        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        Collection<String> excludeUrlPatterns = new LinkedHashSet<>();
        excludeUrlPatterns.add("/login/**");
        excludeUrlPatterns.add("/auth/**");
//      excludeUrlPatterns.add("/logout/**");
//      excludeUrlPatterns.add("/signup/**");
//      excludeUrlPatterns.add("/resources/**");


        return excludeUrlPatterns.stream()
                .anyMatch(pattern -> new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
