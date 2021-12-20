package com.breezelab.sample.config;

import com.breezelab.sample.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
//secured 어노테이션 활성화 어노테이션으로 ROLE controller 등록 @Secured("ROLE_ADMIN")
//여러 가지 등록을 하고 싶으면 prePostEnabled --  @PreAuthorize , @PostAuthorize
// @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')" - 메서드가 실행되기전
// @PostAuthorize() - 메서드가 실행된 후
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                //user 경로는 로그인된 회원 누구나
                .antMatchers("/user/**").authenticated()
                //manamger 경로는 권한 가진 사람만 (manager or admin)
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                //admin 경로는 권한 가진 사람만 (admin)
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                //다른 경로는 접속 허용
                .anyRequest().permitAll()
                //권한이 없을시 권한페이지로 이동할경우 로그인 페이지로 이동
                .and()
                .formLogin()
                .loginPage("/loginForm")
                //.usernameParameter()
                // login 주소가 호출 되면 시큐리티가 낚아채서 대신 로그인 해준다.
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                // 구글 로그인이 완료된 뒤의 후처리가 필요함 Tip. 코드X (엑세스토큰 + 사용자프로필정보 O)
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);


    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("1234")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    // password 암호화
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
}
