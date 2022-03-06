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

        try {
            String token = authorization.substring(7);
            Claims claims = tokenUtils.parse(token);

            // 권한 부여 및 사용자 정보 저장
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(claims.get("id"), null, null));

            filterChain.doFilter(request,response);
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", ResponseType.TOKEN_TIME_OUT);
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            request.setAttribute("exception", ResponseType.SEVER_ERROR);
            filterChain.doFilter(request, response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        Collection<String> excludeUrlPatterns = new LinkedHashSet<>();
        excludeUrlPatterns.add("/login/**");
        excludeUrlPatterns.add("/auth/**");

        return excludeUrlPatterns.stream()
                .anyMatch(pattern -> new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
