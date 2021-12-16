package com.breezelab.sample.controller;

import com.breezelab.sample.config.auth.PrincipalDetails;
import com.breezelab.sample.mappers.UserMapper;
import com.breezelab.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value = "/test/login" , method = RequestMethod.GET)
    @ResponseBody
    public String loginTest(Authentication authentication,
                            @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("test/login ===================================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication :" + principalDetails.getUser());
        System.out.println("userDetails : " + userDetails.getUser());
        return "세션 정보 확인";
    }

    @RequestMapping(value = "/test/oauth/login" , method = RequestMethod.GET)
    @ResponseBody
    public String loginOAuthTest(Authentication authentication,
                            @AuthenticationPrincipal OAuth2User oauth){
        System.out.println("test/login ===================================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication :" + oAuth2User.getAttributes());
        System.out.println("OAuth : " + oauth.getAttributes());
        return "OAuth 세션 정보 확인";
    }


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    @ResponseBody
    // 로그인 된사람만
    public String user(){
        return "user";
    }

    @RequestMapping(value = "/admin" , method = RequestMethod.GET)
    @ResponseBody
    // 권한 로그인
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/manager" , method = RequestMethod.GET)
    @ResponseBody
    // 권한 로그인
    public String manager(){
        return "manager";
    }

    @RequestMapping(value = "/loginForm" , method = RequestMethod.GET)
    public String loginForm(){
        return "loginForm";
    }

    @RequestMapping(value = "/joinForm" , method = RequestMethod.GET)
    public String joinForm(){
        return "joinForm";
    }

    @RequestMapping(value = "/join" , method = RequestMethod.POST)
    // 회원 가입 살행
    public String join(User user){

        // 페스워트 암호화 해야함 - 권한과 인증
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setCreatedAt(LocalDateTime.now());
        user.setRole("ROLE_USER");
        user.setPassword(encPassword);

        System.out.println(user);
        mapper.insertUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getRole(),user.getCreatedAt());
        return "redirect:/loginForm";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/info" ,method = RequestMethod.GET)
    @ResponseBody
    public String info(){
        return "개인정보";
    }

    // 페이지가 실생하기 직전에 참조한다
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/data" ,method = RequestMethod.GET)
    @ResponseBody
    public String data(){
        return "data";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public List<User> sqlTest(){
        return mapper.getUserList();
    }


}
