package main.demo.configuration;

import main.demo.configuration.provider.JWTAuthenticationEntryPoint;
import main.demo.configuration.provider.JWTAuthenticationFilter;
import main.demo.mvc.repository.Repository_User;
import main.demo.utilization.JwtToken;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity // 웹 보안 활성화를 위한 annotation => SpringSecurityFilterChain에 등록됨.
public class Config_Security extends WebSecurityConfigurerAdapter {

    private final JwtToken jwtUtils;
    private final Repository_User userRepository;

    public Config_Security(JwtToken jwtUtils, Repository_User userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    // HttpSecurity
    // A HttpSecurity is similar to Spring Security's XML <http> element in the namespace configuration.
    // It allows configuring web based security for specific http requests
    // By default it will be applied to all requests, but can be restricted using requestMatcher(RequestMatcher) or other similar methods.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin().disable()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/other/mypage").authenticated()
                .antMatchers("/other/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(new JWTAuthenticationFilter(jwtUtils,userRepository), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }

    // WebSecurity
    // Customizations to the WebSecurity can be made by creating a WebSecurityConfigurer,
    // overriding WebSecurityConfigurerAdapter or exposing a WebSecurityCustomizer bean.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login/**", "/auth/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
