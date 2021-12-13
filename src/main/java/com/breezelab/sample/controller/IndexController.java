package com.breezelab.sample.controller;

import com.breezelab.sample.mappers.UserMapper;
import com.breezelab.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//        mapper.insertUser(user.getAccount(),user.getPassword(),user.getEmail(),user.getRole(),user.getCreatedAt());
        return "redirect:/loginForm";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public List<User> sqlTest(){
        return mapper.getUserList();
    }


}
